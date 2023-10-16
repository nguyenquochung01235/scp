package com.scp.web.common;

import java.util.function.Function;

public interface IKeywordCommon {
    public void runKeywordAndIgnoreError(Function function);
    public void renKeywordAndReturnStatus(Function function);
}
