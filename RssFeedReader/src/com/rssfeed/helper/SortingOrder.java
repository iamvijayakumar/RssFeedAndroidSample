package com.rssfeed.helper;

import java.util.Comparator;

import org.json.JSONObject;

public class SortingOrder implements Comparator<RssFeedStructure>{

public int compare(RssFeedStructure o1, RssFeedStructure o2) {
return (o1.getTitle()).compareTo(o2.getTitle());
}
}