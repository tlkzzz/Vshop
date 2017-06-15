package com.Vshop.seller.test;

import com.google.common.collect.Lists;
import com.Vshop.core.jackson.JsonUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ReadArea {
    public static List<Group> read() throws IOException {
            File input = new File(ReadArea.class.getResource("/test/shopnc_area.ftl").getFile());
            Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
            Elements ets = doc.select("li");
            List<Group> groups = Lists.newArrayList();
            for (Element et : ets) {
                Elements daqus = et.select("dt");
                String daqu = daqus.text();//大区域
                Group group = new Group();
                group.setName(daqu);
                System.out.println(daqu);
                List<Province> provinces = Lists.newArrayList();
                Elements shengfens = et.select(".ncsc-province");
                for (Element shengfen :shengfens) {
                    String shengfenStr = shengfen.select(".ncsc-province-tab").text();
                    String shengfenId = shengfen.select(".ncsc-province-tab").select("input").get(0).val();
                    System.out.println("----"+shengfenStr+"["+shengfenId+"]");
                    Province province = new Province();
                    province.setName(shengfenStr);
                    province.setId(Integer.parseInt(shengfenId));
                    List<City> cities = Lists.newArrayList();
                    Elements shis= shengfen.select(".ncsc-citys-sub").select(".areas");
                    System.out.print("--------");
                    for (Element shi : shis) {
                        City city = new City();
                        city.setName(shi.text());
                        String shiId = shi.select("input").get(0).val();
                        city.setId(Integer.parseInt(shiId));
                        cities.add(city);
                        System.out.print(shi.text()+"["+shiId+"]"+" *** ");
                    }
                    province.setCities(cities);
                    provinces.add(province);

                    System.out.println();
                }
                group.setProvinces(provinces);
                System.out.println("==============");
                groups.add(group);
            }

            System.out.println(JsonUtils.toJsonStr(groups));
        return groups;
    }
}

