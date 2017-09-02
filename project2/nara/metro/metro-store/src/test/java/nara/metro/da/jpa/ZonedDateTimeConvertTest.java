package nara.metro.da.jpa;

import com.fatboyindustrial.gsonjavatime.Converters;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import nara.metro.domain.entity.LoginTime;
import nara.share.util.json.JsonUtil;
import org.junit.Assert;
import org.junit.Test;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class ZonedDateTimeConvertTest {

    @Test
    public void testZonedDateTime() {
        //
        Gson gson = Converters.registerZonedDateTime(new GsonBuilder()).create();

        ZonedDateTime zdt = ZonedDateTime.now();
        String zdtJson = gson.toJson(zdt);
        System.out.println(zdtJson);
        ZonedDateTime zdt2 = gson.fromJson(zdtJson, ZonedDateTime.class);
        System.out.println("equals zdt:"+zdt.equals(zdt2));
        Assert.assertEquals(zdt, zdt2);

        LoginTime loginTime = new LoginTime("CITIZEN", ZonedDateTime.now());
        String json = gson.toJson(loginTime);
        System.out.println(json);

        LoginTime loginTime2 = gson.fromJson(json, LoginTime.class);
        Assert.assertEquals(loginTime.getTime(), loginTime2.getTime());
        System.out.println("equals zdt:"+zdt.equals(loginTime.getTime()));
    }

    @Test
    public void testStringList() {
        List<String> names = new ArrayList<>();
        names.add("aaa");
        names.add("bbb");
        names.add("ccc");

        String json = JsonUtil.toJson(names);
        System.out.println(json);

        names = JsonUtil.fromJsonList(json, String.class);
        Assert.assertEquals(3, names.size());
        Assert.assertEquals("aaa", names.get(0));
        Assert.assertEquals("bbb", names.get(1));
        Assert.assertEquals("ccc", names.get(2));
    }
}
