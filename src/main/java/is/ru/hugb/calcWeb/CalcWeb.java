package is.ru.hugb.calcWeb;

import java.net.URLDecoder;
import static spark.Spark.*;

public class CalcWeb {
    public static void main(String[] args) {
      staticFileLocation("/public");
      port(readPortOrDefault());

      Calculator Calc = new Calculator();

      post(
        "/add",
        (req, res) -> {
          String number = req.queryParams("number");
          String result = number + " = " + Calc.add(number);
          return result;
        }
      );

      get(
        "/add/:numbers",
        (req, res) -> {
          String number = req.params(":numbers");
          return Calc.add(number);
        }
      );
    }

    static int readPortOrDefault() {
      ProcessBuilder psb = new ProcessBuilder();
      if (psb.environment().get("PORT") != null) {
        return Integer.parseInt(psb.environment().get("PORT"));
      }
      return 4567;
    }

}
