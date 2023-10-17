package com.scp.web.common.implement;

import com.scp.web.common.IKeywordCommon;
import java.io.*;
import java.nio.file.Paths;
import java.util.function.Function;
import org.json.JSONObject;

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
    public Object readFileAndConvertToJsonObject(String fileLocate) {
        String absolutedpath = Paths.get("").toAbsolutePath().toString() + "\\src\\test\\java" + Paths.get(fileLocate);
        File file = new File(absolutedpath);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String st;
        String data = "";
        while (true)

        {
            try {
                if (!((st = br.readLine()) != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            data = data +st;
        }

        JSONObject object = new JSONObject(data);


            return object;
    }
}
