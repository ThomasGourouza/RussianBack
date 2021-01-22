package com.cavalierfou.russianback.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class Utils {

    public List<Long> mapRequestParamIdsToListIds(String ids) {
        return (ids != null)
                ? Arrays.asList(ids.split(",")).stream().map(id -> Long.parseLong(id)).collect(Collectors.toList())
                : new ArrayList<>();
    }
}
