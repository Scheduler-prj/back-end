package PlanQ.PlanQ.routine;

public enum Dotw {
    MON, TUE, WED, THU, FRI, SAT, SUN;

    public static String changeString(Dotw dotw){
        return dotw.toString();
    }

    public static Dotw fromString(String day) {
        return switch (day.toUpperCase()) {
            case "MONDAY" -> MON;
            case "TUESDAY" -> TUE;
            case "WEDNESDAY" -> WED;
            case "THURSDAY" -> THU;
            case "FRIDAY" -> FRI;
            case "SATURDAY" -> SAT;
            case "SUNDAY" -> SUN;
            default -> throw new IllegalArgumentException("없는 날짜 " + day);
        };
    }
}
