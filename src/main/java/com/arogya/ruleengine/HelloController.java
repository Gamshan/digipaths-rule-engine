package com.arogya.ruleengine;

import org.springframework.web.bind.annotation.*;
import org.graalvm.polyglot.*;
import org.graalvm.polyglot.proxy.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class HelloController {

    @RequestMapping("/")
    String hello(@RequestParam Integer x, @RequestParam Integer y ) {
        String JS_CODE = "(function myFun(x, y){return x + y;})";

        try (Context context = Context.create()) {
            Value value = context.eval("js", JS_CODE);
            Value newV = value.execute(x,y);
            return x + "+" + y + "= "  + newV;
        }
    }

    public List<Rule> getRuleList () {
        List<Rule> ruleList = new ArrayList<>();
         Rule rule1 = new Rule();
         rule1.setFunction("(function isShowHemoglobinRule(obsList){" +
                 "let jsObsList =  Array.from(obsList);"+
                 "let lastObs = jsObsList.reduce((latestObj, currentObj) => {\n" +
                 "    return new Date(currentObj.lastCreated) > new Date(latestObj.lastCreated)\n" +
                 "        ? currentObj\n" +
                 "        : latestObj;\n" +
                 "});" +
                 "const oneYearAgo = new Date();\n" +
                 "oneYearAgo.setFullYear(new Date().getFullYear() - 1);\n" +
                 "console.log(oneYearAgo, new Date(lastObs.lastCreated)); \n" +
                 "return new Date(lastObs.lastCreated) < oneYearAgo; \n" +
                 "})");

         List<String> requiredAdditionalFields = new ArrayList<>();
        requiredAdditionalFields.add("age");
         requiredAdditionalFields.add("gender");

         rule1.setRequiredAdditionalFields(requiredAdditionalFields);
         rule1.setMessage("HbA1c Result is Outdated");
         rule1.setRecommendation("<p>The patient’s last <b>HbA1c</b> test was performed over 12 months ago. Consider ordering a new test to assess current glycemic control.</p>");

         ruleList.add(rule1);

         return ruleList;

    }

    @PostMapping("/digipaths")
    List<DigiResponse> digipaths(@RequestBody DigiRequest digiRequest ) {

        List<DigiResponse> digiResponseList = new ArrayList<>();
//        List<Rule> ruleList = getRuleList();
//        System.out.println("digiRequest" + digiRequest.getObs());
//        ruleList.forEach(rule -> {
//            boolean isValid = validReqParams(rule.getRequiredAdditionalFields(), digiRequest);
//            if(isValid){
//                try (Context context = Context.newBuilder("js").allowAllAccess(true).build()) {
//                    Value value = context.eval("js", rule.getFunction());
//                    Value newV = value.execute(digiRequest.getObs());
//                    if(newV != null && newV.asBoolean()){
//                        System.out.println("XXXXX" + newV);
//                        DigiResponse digiResponse = new DigiResponse();
//
//                        digiResponse.setDate(new Date());
//                        digiResponse.setMessage(rule.getMessage());
//                        digiResponse.setRecommendation(rule.getRecommendation());
//                        digiResponseList.add(digiResponse);
//                    }
//
//                }
//            }
//        });




        try {
            loadJsFile();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }






        return digiResponseList;
    }

    public boolean validReqParams(List<String> requiredFields, DigiRequest digiRequest){
        if(digiRequest == null || digiRequest.getObs() == null || digiRequest.getAdditionalParams() == null)
            return false;
       return  requiredFields.stream()
                .allMatch(digiRequest.getAdditionalParams()::containsKey);
    }


    public void loadJsFile() throws IOException {
            Context context = Context.newBuilder("js").option("js.esm-eval-returns-exports", "true").allowIO(true).build();
            File jsFile = new File("/Users/gamshgamshan/Downloads/proforma.js");

            Source source = Source.newBuilder("js", jsFile).mimeType("application/javascript+module").build();
            Value value = context.eval(source);

//            Value exe = value.getMember("foo");
              Value exe = value.getMember("Protocol");

            System.out.println(exe);
            context.close();
        }


}