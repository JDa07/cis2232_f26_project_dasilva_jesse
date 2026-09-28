package info.hccis.model.jpa;

import java.util.Objects;
import java.util.Scanner;

public class SkillsAssessmentSquashTechnical {

    private Integer id=0;
    private String assessmentDate;
    private String createdDateTime;
    private String athleteName;
    private String assessorName;
    private Integer forehandDrives;
    private Integer backhandDrives;
    private Integer forehandVolleyMax;
    private Integer forehandVolleySum;
    private Integer backhandVolleyMax;
    private Integer backhandVolleySum;
    private Integer technicalScore;

    public void getInformation() {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter assessment date (YYYY-MM-DD): ");
        assessmentDate = input.nextLine();

        System.out.print("Enter created date/time (YYYY-MM-DD HH:MM): ");
        createdDateTime = input.nextLine();

        System.out.print("Enter athlete name: ");
        athleteName = input.nextLine();

        System.out.print("Enter assessor name: ");
        assessorName = input.nextLine();

        System.out.print("Enter forehand drives: ");
        forehandDrives = Integer.parseInt(input.nextLine());

        System.out.print("Enter backhand drives: ");
        backhandDrives = Integer.parseInt(input.nextLine());

        System.out.print("Enter forehand volley max: ");
        forehandVolleyMax = Integer.parseInt(input.nextLine());

        System.out.print("Enter forehand volley sum: ");
        forehandVolleySum = Integer.parseInt(input.nextLine());

        System.out.print("Enter backhand volley max: ");
        backhandVolleyMax = Integer.parseInt(input.nextLine());

        System.out.print("Enter backhand volley sum: ");
        backhandVolleySum = Integer.parseInt(input.nextLine());
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAssessmentDate() {
        return assessmentDate;
    }

    public void setAssessmentDate(String assessmentDate) {
        this.assessmentDate = assessmentDate;
    }

    public String getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getAthleteName() {
        return athleteName;
    }

    public void setAthleteName(String athleteName) {
        this.athleteName = athleteName;
    }

    public String getAssessorName() {
        return assessorName;
    }

    public void setAssessorName(String assessorName) {
        this.assessorName = assessorName;
    }

    public Integer getForehandDrives() {
        return forehandDrives;
    }

    public void setForehandDrives(Integer forehandDrives) {
        this.forehandDrives = forehandDrives;
    }

    public Integer getBackhandDrives() {
        return backhandDrives;
    }

    public void setBackhandDrives(Integer backhandDrives) {
        this.backhandDrives = backhandDrives;
    }

    public Integer getForehandVolleyMax() {
        return forehandVolleyMax;
    }

    public void setForehandVolleyMax(Integer forehandVolleyMax) {
        this.forehandVolleyMax = forehandVolleyMax;
    }

    public Integer getForehandVolleySum() {
        return forehandVolleySum;
    }

    public void setForehandVolleySum(Integer forehandVolleySum) {
        this.forehandVolleySum = forehandVolleySum;
    }

    public Integer getBackhandVolleyMax() {
        return backhandVolleyMax;
    }

    public void setBackhandVolleyMax(Integer backhandVolleyMax) {
        this.backhandVolleyMax = backhandVolleyMax;
    }

    public Integer getBackhandVolleySum() {
        return backhandVolleySum;
    }

    public void setBackhandVolleySum(Integer backhandVolleySum) {
        this.backhandVolleySum = backhandVolleySum;
    }

    public Integer getTechnicalScore() {
        return technicalScore;
    }

    public void setTechnicalScore(Integer technicalScore) {
        this.technicalScore = technicalScore;
    }

    @Override
    public String toString() {
        return "SkillsAssessmentSquashTechnical\n" +
                "    assessmentDate     = '" + assessmentDate + "',\n" +
                "    createdDateTime    = '" + createdDateTime + "',\n" +
                "    athleteName        = '" + athleteName + "',\n" +
                "    assessorName       = '" + assessorName + "',\n" +
                "    forehandDrives     = " + forehandDrives + ",\n" +
                "    backhandDrives     = " + backhandDrives + ",\n" +
                "    forehandVolleyMax  = " + forehandVolleyMax + ",\n" +
                "    forehandVolleySum  = " + forehandVolleySum + ",\n" +
                "    backhandVolleyMax  = " + backhandVolleyMax + ",\n" +
                "    backhandVolleySum  = " + backhandVolleySum + ",\n" +
                "    technicalScore     = " + technicalScore + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof SkillsAssessmentSquashTechnical)) return false;
        SkillsAssessmentSquashTechnical that = (SkillsAssessmentSquashTechnical) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}