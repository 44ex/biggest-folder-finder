public class SizeCalculator {
//    // Вариант 1
//    public static String getHumanReadableSize(long size){
//        String[] sizeMarks = {"B","Kb","Mb","Gb","Tb"};
//        for(int i=0; i<sizeMarks.length; i++){
//            double value = ((double)size)/Math.pow(1024,i);
//            if(value < 1024){
//                return String.format("%.2f%s",value, sizeMarks[i]);
//            }
//        }
//        return "Размер от пентабайта и больше";
//    }
//-----------------------------------------------------------------------------------
////    // Вариант 2
//    public static String getHumanReadableSize(long size){
//        String[] sizeMarks = {"B","Kb","Mb","Gb","Tb"};
//        String strSize = Long.toString(size);
//        int i;
//        if(strSize.length() <= 3)i=0;
//        else
//        if(strSize.length() <= 6)i=1;
//        else
//        if(strSize.length() <= 9)i=2;
//        else
//        if(strSize.length() <=12)i=3;
//        else
//        if(strSize.length() <=15)i=4;
//        else
//            return "Размер от пентабайта и больше";
//        return String.format("%.2f%s",(double)size/Math.pow(1024,i), sizeMarks[i]);
//    }
//-----------------------------------------------------------------------------------
//    // Вариант 3
    public static String getHumanReadableSize(long size){
        String[] sizeMarks = {"B","Kb","Mb","Gb","Tb"};
        String strSize = Long.toString(size);
        for(int i=0; i<5; i++) {
            if (strSize.length()/(3*(i+1)) == 0) {
                return String.format("%.2f%s",(double)size/Math.pow(1024,i), sizeMarks[i]);
            }
        }
        return "Размер от пентабайта и больше";
    }
    // TODO: //4B, 234Kb, 36Mb, 34Gb, 42Tb
    //       //4B, 234K,  36M,  34G,  42T
    public static long getSizeFromHumanReadable(String size){
        int multiplier = -1;
        // предполагаем, что size корректен, т.е. меньше пентабайта
        if(size.contains("B"))multiplier = 0; else
        if(size.contains("K"))multiplier = 1; else
        if(size.contains("M"))multiplier = 2; else
        if(size.contains("G"))multiplier = 3; else
        if(size.contains("T"))multiplier = 4;

        long length;
        if(size.contains(",")){ // если size содержит дробную часть
            String part[] = size.split(",");
            part[1] = part[1].replaceAll("[^0-9]", "");
            length = (int) Math.pow(1024, multiplier) *
                    Long.valueOf(part[0])
                    + (int) Math.pow(1024, multiplier) *
                    Long.valueOf(part[1])/(int) Math.pow(10, part[1].length())
            ;
        }
        else { // если size содержит только целую часть
            length = (int) Math.pow(1024, multiplier) *
                    Long.valueOf(size.replaceAll("[^0-9]", ""));
        }
        return length;
    }

}
