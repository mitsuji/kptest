import java.net.URL;
import java.net.HttpURLConnection;

public class Main {
  private static final int connectTimeout_ = 500;
  private static final int readTimeout_ = 500;
    
  public static void main(String[] args) {
      try {
	  for (int i = 0; i < 10; ++i) {
	      doHttp("http://mitsuji.org:9001/fooo" + i);
//	      Thread.sleep(500);
	  }
      } catch (Exception ex) {
	  ex.printStackTrace();
      }
  }

  private static void doHttp (String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(connectTimeout_);
        connection.setReadTimeout(readTimeout_);
        connection.setRequestMethod("GET");
	connection.addRequestProperty("URL",urlString);
        try {
          connection.connect();
          int statusCode = connection.getResponseCode();
          if (statusCode == HttpURLConnection.HTTP_OK) {
	      System.out.println("OK");
          } else {
	      System.out.println("NG status:" + statusCode);
          }
	  connection.getInputStream().close();
        } catch (Exception ex) {
          throw ex;
        } finally {
	  connection.disconnect();
        }
  }
    
}
