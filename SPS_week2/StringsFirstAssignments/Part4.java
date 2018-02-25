import edu.duke.*;

public class Part4 {
    public void getYouTubeLink () {
        /**
        *Input: none
        *Output: Print out the url of the youtube website
        */

        String youtubestring = "youtube.com";
        String lowers = "";
        int startIndex = 0;
        int endIndex = 0;
        String youtubeurl = "";

        URLResource ur = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        for (String s : ur.lines()) {
            // System.out.println(s);
            lowers = s.toLowerCase();
            if (lowers.indexOf(youtubestring) != -1) {
                //System.out.println(s);
                startIndex = s.indexOf("href") + 6;
                endIndex = s.indexOf("\"", startIndex);

                youtubeurl = s.substring(startIndex, endIndex);
                System.out.println(youtubeurl);
            }
         }
        System.out.println("======================================");

    }
}

 