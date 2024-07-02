package org.gogroup.services;

import org.gogroup.server.Response;

import java.util.ArrayList;
import java.util.HashMap;

public class GameResultParser extends Parser{

    public String getGameResult(Response response) {
        System.out.println(response);
        return "lala";
        /*
        int hostPoints = (Integer) response.getBody().get("hostPoints");
        int guestPoints = (Integer) response.getBody().get("guestPoints");
        HashMap<String, Object> results = new HashMap<>();
        results.put("hostPoints", hostPoints);
        results.put("guestPoints", guestPoints);
        return results;

         */
    }

}
