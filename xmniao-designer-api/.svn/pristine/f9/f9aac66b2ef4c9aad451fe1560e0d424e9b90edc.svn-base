package com.xmn.designer.base;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.HashMap;

/**
 * create 2016/11/23
 *
 * @author yangQiang
 */

public class ExpressInfo {


    private static ExpressInfo expressInfo;
    public static HashMap<String, String> expType;
    private final static String expressTypeJson =
            "{\"status\":\"0\",\"msg\":\"ok\",\"result\":[{\"name\":\"德邦\",\"type\":\"DEPPON\",\"letter\":\"D\",\"tel\":\"95353\",\"number\":\"330060412\"},{\"name\":\"D速\",\"type\":\"DEXP\",\"letter\":\"D\",\"tel\":\"0531-88636363\",\"number\":\"316J103367777\"},{\"name\":\"DHL\",\"type\":\"DHL\",\"letter\":\"D\",\"tel\":\"800-810-8000400-810-8000\",\"number\":\"5846399171\"},{\"name\":\"EMS\",\"type\":\"EMS\",\"letter\":\"E\",\"tel\":\"40080-11183\",\"number\":\"1082490090717\"},{\"name\":\"FedEx\",\"type\":\"FEDEX\",\"letter\":\"F\",\"tel\":\"800-988-1888400-886-1888\",\"number\":\"120949498648\"},{\"name\":\"国通\",\"type\":\"GTO\",\"letter\":\"G\",\"tel\":\"4001-111-123\",\"number\":\"2639589835\"},{\"name\":\"能达\",\"type\":\"ND56\",\"letter\":\"G\",\"tel\":\"400-6886-765\",\"number\":\"\"},{\"name\":\"汇通\",\"type\":\"HTKY\",\"letter\":\"H\",\"tel\":\"4009565656\",\"number\":\"210937745480\"},{\"name\":\"京东\",\"type\":\"JD\",\"letter\":\"J\",\"tel\":\"\",\"number\":\"12290972964\"},{\"name\":\"嘉里物流\",\"type\":\"KERRY\",\"letter\":\"J\",\"tel\":\"852-2410-3600\",\"number\":\"316B455817673\"},{\"name\":\"佳吉\",\"type\":\"JIAJI\",\"letter\":\"J\",\"tel\":\"400-820-5566\",\"number\":\"729976312\"},{\"name\":\"京广\",\"type\":\"KKE\",\"letter\":\"J\",\"tel\":\"852-23329918\",\"number\":\"7102293245\"},{\"name\":\"佳怡\",\"type\":\"JIAYI\",\"letter\":\"J\",\"tel\":\"400-631-9999\",\"number\":\"\"},{\"name\":\"加运美\",\"type\":\"TMS\",\"letter\":\"J\",\"tel\":\"0769-85515555\",\"number\":\"2197050107\"},{\"name\":\"急先达\",\"type\":\"JOUST\",\"letter\":\"J\",\"tel\":\"400-694-1256\",\"number\":\"\"},{\"name\":\"跨越\",\"type\":\"KYEXPRESS\",\"letter\":\"K\",\"tel\":\"4008-098-098\",\"number\":\"2628904\"},{\"name\":\"快捷\",\"type\":\"FASTEXPRESS\",\"letter\":\"K\",\"tel\":\"4008-333-666\",\"number\":\"536135784093\"},{\"name\":\"龙邦\",\"type\":\"LBEX\",\"letter\":\"L\",\"tel\":\"021-59218889\",\"number\":\"686013186447\"},{\"name\":\"全峰\",\"type\":\"QFKD\",\"letter\":\"Q\",\"tel\":\"4001-000-001\",\"number\":\"720166045326\"},{\"name\":\"全晨\",\"type\":\"QCKD\",\"letter\":\"Q\",\"tel\":\"0769-82026703\",\"number\":\"2233244233\"},{\"name\":\"全一\",\"type\":\"APEX\",\"letter\":\"Q\",\"tel\":\"400-663-1111\",\"number\":\"112276086230\"},{\"name\":\"如风达\",\"type\":\"RFD\",\"letter\":\"R\",\"tel\":\"400-010-6660\",\"number\":\"11604247156509\"},{\"name\":\"顺丰\",\"type\":\"SFEXPRESS\",\"letter\":\"S\",\"tel\":\"95338\",\"number\":\"664934099535\"},{\"name\":\"申通\",\"type\":\"STO\",\"letter\":\"S\",\"tel\":\"95543\",\"number\":\"3310265451646\"},{\"name\":\"三态\",\"type\":\"SFC\",\"letter\":\"S\",\"tel\":\"400-881-8106\",\"number\":\"\"},{\"name\":\"盛辉\",\"type\":\"SHENGHUI\",\"letter\":\"S\",\"tel\":\"400-822-2222\",\"number\":\"240815442\"},{\"name\":\"速尔\",\"type\":\"SURE\",\"letter\":\"S\",\"tel\":\"400-158-9888\",\"number\":\"880218258595\"},{\"name\":\"天天\",\"type\":\"TTKDEX\",\"letter\":\"T\",\"tel\":\"4001-888-888\",\"number\":\"560516990584\"},{\"name\":\"天地华宇\",\"type\":\"HOAU\",\"letter\":\"T\",\"tel\":\"400-808-6666\",\"number\":\"020286402\"},{\"name\":\"TNT\",\"type\":\"TNT\",\"letter\":\"T\",\"tel\":\"800-820-9868\",\"number\":\"335939905\"},{\"name\":\"UPS\",\"type\":\"UPS\",\"letter\":\"U\",\"tel\":\"800-820-8388400-820-8388\",\"number\":\"1ZV6509Y0468336755\"},{\"name\":\"万象\",\"type\":\"EWINSHINE\",\"letter\":\"W\",\"tel\":\"400-820-8088\",\"number\":\"2225195562855\"},{\"name\":\"万家物流\",\"type\":\"WANJIA\",\"letter\":\"W\",\"tel\":\"4001-156-561\",\"number\":\"31000001425628\"},{\"name\":\"新邦\",\"type\":\"XBWL\",\"letter\":\"X\",\"tel\":\"4008-000-222\",\"number\":\"23624522\"},{\"name\":\"圆通\",\"type\":\"YTO\",\"letter\":\"Y\",\"tel\":\"021-69777888021-69777999\",\"number\":\"100587985104\"},{\"name\":\"韵达\",\"type\":\"YUNDA\",\"letter\":\"Y\",\"tel\":\"95546\",\"number\":\"1202237859178\"},{\"name\":\"邮政包裹\",\"type\":\"CHINAPOST\",\"letter\":\"Y\",\"tel\":\"11185\",\"number\":\"9610027635439\"},{\"name\":\"源安达\",\"type\":\"YADEX\",\"letter\":\"Y\",\"tel\":\"0769-85021875\",\"number\":\"\"},{\"name\":\"运通\",\"type\":\"YTEXPRESS\",\"letter\":\"Y\",\"tel\":\"0769-81156999\",\"number\":\"666316719\"},{\"name\":\"越丰\",\"type\":\"YFEXPRESS\",\"letter\":\"Y\",\"tel\":\"(852)23909969\",\"number\":\"\"},{\"name\":\"优速\",\"type\":\"UC56\",\"letter\":\"Y\",\"tel\":\"400-1111-119\",\"number\":\"518166035710\"},{\"name\":\"中通\",\"type\":\"ZTO\",\"letter\":\"Z\",\"tel\":\"95311\",\"number\":\"728694125248\"},{\"name\":\"宅急送\",\"type\":\"ZJS\",\"letter\":\"Z\",\"tel\":\"400-6789-000\",\"number\":\"6521513331\"},{\"name\":\"中铁\",\"type\":\"CRE\",\"letter\":\"Z\",\"tel\":\"95572\",\"number\":\"0698042\"}]}";

    public String getExpressTypeJson() {
        return expressTypeJson;
    }

    private ExpressInfo( ) {

    };

    public static ExpressInfo getInstance() {
        if(expressInfo==null){
            expressInfo =new ExpressInfo();
            expressInfo.init();
        }
        return expressInfo;
    }

    public HashMap<String, String> getExpType() {
        return expType;
    }


    public void init() {
        JSONObject jsonObject = JSONObject.fromObject(expressTypeJson);
        JSONArray result = jsonObject.getJSONArray("result");

        expType = new HashMap<>();
        for (int i = 0; i < result.size(); i++) {
            JSONObject itme = result.getJSONObject(i);
            expType.put(itme.get("type").toString().toLowerCase(), itme.get("name").toString());
        }
    }



}
