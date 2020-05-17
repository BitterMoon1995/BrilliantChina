package util;

import lombok.Data;

@Data
public class QueryParams {
    Integer pageNum;
    Integer pageSize;
    String condition;
    String username;
}
