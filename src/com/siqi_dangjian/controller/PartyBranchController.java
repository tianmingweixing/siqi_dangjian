package com.siqi_dangjian.controller;

import com.siqi_dangjian.bean.PartyBranch;
import com.siqi_dangjian.service.IPartyBranchService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/partyBranch")
public class PartyBranchController extends BaseController{

    @Autowired
    private IPartyBranchService partyBranchService;

    /**
     * 查询党支部信息
     * @param name
     * @param founding_time
     * @param change_time
     * @param limit
     * @param page
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public ModelMap getPartBranchList(@RequestParam(value = "name",required = false)String name,
                                      @RequestParam(value = "founding_time",required = false)String founding_time,
                                      @RequestParam(value = "change_time",required = false)String change_time,
                                      @RequestParam(value="limit", required=false)Integer limit,
                                      @RequestParam(value="page", required=false)Integer page){

        modelMap = new ModelMap();

        Map blurMap = new HashMap<>();
        Map dateMap = new HashMap<>();
        Map intMap  = new HashMap<>();

//        if(StringUtils.isNotEmpty(type)) {
//            intMap.put("type", type);
//        }
//        if(StringUtils.isNotEmpty(isEnd)) {
//            intMap.put("is_End", isEnd);
//        }

        if(StringUtils.isNotEmpty(name)) {
            blurMap.put("name", name);
        }

        if(StringUtils.isNotEmpty(founding_time)) {
            dateMap.put("founding_time", founding_time);
        }
        if(StringUtils.isNotEmpty(change_time)) {
            dateMap.put("change_time", change_time);
        }
        try {
            Map map = partyBranchService.selectAll(blurMap,intMap,dateMap,limit,page);

            List list = (List<PartyBranch>) map.get("list");
            Integer count = (int) map.get("count");
            setData("data", list);
            setData("count", count);
            setSuccess();
        }catch (Exception e){
            e.printStackTrace();
            setFail();
        }

        return modelMap;
    }
}
