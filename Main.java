import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

    public class Main {



        public static void main(String[] args) throws IOException {

            String group_id = "typical_nn";
            //String group_id = "197497128";
            String access_token = "d85da786021ce4581cd1df98822ef08ee9a58c9686a620e0231ec24d6acf228e5c50129ad3d41fa6640b5";
            String api_version = "5.122";
            int count;
            String url = "https://api.vk.com/method/groups.getMembers?access_token="
                    + access_token + "&&v=" + api_version + "&&group_id=" + group_id
                    + "&&count=1000&&offset=2000&&fields=sex,contacts,bdate";

            URL UrlObj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) UrlObj.openConnection();

            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;

            StringBuilder JsonStringObj = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                JsonStringObj.append(inputLine);
            }
            in.close();

            System.out.println(JsonStringObj.toString());
            JSONObject MainObj = new JSONObject(JsonStringObj.toString());

            JSONObject ResponseObj = MainObj.getJSONObject("response");

            JSONArray ItemsArray = ResponseObj.getJSONArray("items");

            int k = 0;
            for (int i = 0; i < ItemsArray.length(); i++){

                JSONObject CurrentObj = ItemsArray.getJSONObject(i);

                if (CurrentObj.has("mobile_phone") && CurrentObj.has("bdate")){

                    String bdate = CurrentObj.getString("bdate");
                    String mobile = CurrentObj.getString("mobile_phone");


                    if (((bdate.length() >= 8) && (bdate.length() <=10)) && ((mobile.length()>=10) && (mobile.length()<=12))) {

                        System.out.println(CurrentObj.getString("first_name"));
                        System.out.println(CurrentObj.getString("last_name"));
                        System.out.println(CurrentObj.getInt("sex"));
                        System.out.println(CurrentObj.getString("bdate"));
                        System.out.println(mobile);
                        System.out.println();
                        k++;
                    }
                }

            }

            System.out.println(k);

        }
    }
