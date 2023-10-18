package com.scp.web.common.implement;

import com.scp.web.common.IKeywordCommon;
import java.io.*;
import java.nio.file.Paths;
import java.util.function.Function;
import org.json.JSONObject;
import org.testng.Assert;

public class KeywordCommon implements IKeywordCommon {
    public KeywordCommon(){
    }

    @Override
    public void runKeywordAndIgnoreError(Function function) {
        try{

        }catch (Exception e){

        }
    }

    @Override
    public void renKeywordAndReturnStatus(Function function) {

    }

    @Override
    public Object readJsonFileAndConvertToJsonObject(String fileLocate) {
        String absolutedpath = Paths.get("").toAbsolutePath().toString() + "\\src\\test\\java" + Paths.get(fileLocate);

        Assert.assertTrue(absolutedpath.matches(".+\\.json"), "This file is not supported, Please input Json file: " + absolutedpath);

        File file = new File(absolutedpath);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String line;
        StringBuilder data = new StringBuilder();
        while (true)
        {
            try {
                if ((line = br.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            data.append(line);
        }
        JSONObject result = null;
        try{
            result = new JSONObject(data.toString());
        }catch(Exception e) {
            Assert.fail("This file is not Json format, Please check : " + absolutedpath);
        }
        return result;
    }
}
