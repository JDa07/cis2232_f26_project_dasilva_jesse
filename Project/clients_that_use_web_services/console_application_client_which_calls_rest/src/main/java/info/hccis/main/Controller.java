package info.hccis.main;

import com.google.gson.Gson;
import info.hccis.model.jpa.SkillsAssessmentSquashTechnical;
import info.hccis.student.util.UtilityRest;
import org.json.JSONArray;

import java.util.Scanner;

public class Controller {

    final public static String MENU = "\nMain Menu \nA) Add\n"
            + "U) Update (FUTURE)\n"
            + "V) View\n"
            + "D) Delete\n"
            + "X) eXit";
    final static Scanner input = new Scanner(System.in);
    private static final String URL_STRING = "http://localhost:8081/api/SASTService/v1/assessments";

    public static void main(String[] args) {
        boolean endProgram = false;
        do {
            System.out.println(MENU);
            String choice = input.nextLine();
            SkillsAssessmentSquashTechnical sast;
            String url;
            switch (choice.toUpperCase()) {
                case "A":
                    sast = create();
                    url = URL_STRING;
                    System.out.println("Url=" + url);
                    SkillsAssessmentSquashTechnical sastTemp = (SkillsAssessmentSquashTechnical) UtilityRest.addUsingRest(url, sast);
                    if(sastTemp != null) {
                        System.out.println("Added new entity:" + sastTemp.toString());
                    }
                    break;
//                case "U":
//                    camper = createCamper();
//                    url = URL_STRING;
//                    System.out.println("Url="+url);
//                    UtilityRest.addUsingRest(url, camper);
                case "D":
                    System.out.println("Enter id to delete");
                    Scanner input = new Scanner(System.in);
                    int id = input.nextInt();
                    input.nextLine();  //burn
                    UtilityRest.deleteUsingRest(URL_STRING, id);
                    break;
                case "V":
                    String jsonReturned = UtilityRest.getJsonFromRest(URL_STRING);
                    //**************************************************************
                    //Based on the json string passed back, loop through each json
                    //object which is a json string in an array of json strings.
                    //*************************************************************
                    JSONArray jsonArray = new JSONArray(jsonReturned);
                    //**************************************************************
                    //For each json object in the array, show the first and last names
                    //**************************************************************
                    System.out.println("Here are the rows");
                    Gson gson = new Gson();
                    for (int currentIndex = 0; currentIndex < jsonArray.length(); currentIndex++) {
                        SkillsAssessmentSquashTechnical current = gson.fromJson(jsonArray.getJSONObject(currentIndex).toString(), SkillsAssessmentSquashTechnical.class);
                        System.out.println(current.toString());
                    }
                    break;

                case "X":
                    endProgram = true;
                    break;
                default:
                    System.out.println("INVALID OPTION");
            }
        } while (!endProgram);
    }

    /**
     * Create an object by passing asking user for input.
     *
     * @return object
     * @author BJM
     * @since 20171117
     */
    public static SkillsAssessmentSquashTechnical create() {
        SkillsAssessmentSquashTechnical sast = new SkillsAssessmentSquashTechnical();
        sast.getInformation();
        return sast;
    }

}
