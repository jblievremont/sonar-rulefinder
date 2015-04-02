package com.sonarsource.rulefinder;

import org.sonar.api.rules.Rule;
import org.sonar.api.rules.RuleFinder;
import org.sonar.api.rules.RuleQuery;
import org.sonar.api.server.ws.Request;
import org.sonar.api.server.ws.RequestHandler;
import org.sonar.api.server.ws.Response;
import org.sonar.api.server.ws.WebService;
import org.sonar.api.utils.text.JsonWriter;

public class RuleWs implements WebService {

  private final RuleFinder ruleFinder;

  public RuleWs(RuleFinder ruleFinder) {
    this.ruleFinder = ruleFinder;
  }

  public void define(Context context) {
    NewController controller = context.createController("api/rulefinder");
    controller.createAction("list")
      .setHandler(new RequestHandler() {
        public void handle(Request request, Response response) throws Exception {
          JsonWriter json = response.newJsonWriter().beginArray();
          for (Rule rule : ruleFinder.findAll(RuleQuery.create())) {
            json.value(rule.getKey().toString());
          }
          json.endArray().close();
        }
      });
    controller.done();
  }

}
