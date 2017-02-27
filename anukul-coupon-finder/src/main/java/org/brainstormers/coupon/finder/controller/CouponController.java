package org.brainstormers.coupon.finder.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.brainstormers.coupon.finder.data.CouponData;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@RestController
public class CouponController {
	@RequestMapping("/latestCoupons")
	public static String latestCoupons() throws JSONException
	{
		StringBuffer result = new StringBuffer();
		 
		try {
			HttpResponse<JsonNode> response = Unirest.get("https://27coupons.p.mashape.com/coupons/latest/?key=eea4e052ae1c74cfc88e05c485e20b3efdb00991")
					.header("X-Mashape-Key", "z5XgXqWT9bmshWNHW0Y2FFfzjXPtp1LSS9bjsnMsQTuohp21nV")
					.header("Accept", "application/json")
					.asJson();
			JSONObject jsonObj = new JSONObject(response.getBody().toString());	
			if(jsonObj !=null)	
			{
				result = buildResponse(jsonObj);
			}
					
			
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result.toString();
	}
	
	@RequestMapping("/popularCoupons")
	public static String popularCoupons() throws JSONException
	{
		StringBuffer result = new StringBuffer();
		 
		try {
			HttpResponse<JsonNode> response = Unirest.get("https://27coupons.p.mashape.com/coupons/popular/?key=eea4e052ae1c74cfc88e05c485e20b3efdb00991")
					.header("X-Mashape-Key", "z5XgXqWT9bmshWNHW0Y2FFfzjXPtp1LSS9bjsnMsQTuohp21nV")
					.header("Accept", "application/json")
					.asJson();
			JSONObject jsonObj = new JSONObject(response.getBody().toString());	
			if(jsonObj !=null)	
			{
				result = buildResponse(jsonObj);
			}
					
			
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result.toString();
	}
	
	@RequestMapping("/trendingCoupons")
	public static String trendingCoupons() throws JSONException
	{
		StringBuffer result = new StringBuffer();
		 
		try {
			HttpResponse<JsonNode> response = Unirest.get("https://27coupons.p.mashape.com/coupons/trending/?key=eea4e052ae1c74cfc88e05c485e20b3efdb00991")
					.header("X-Mashape-Key", "z5XgXqWT9bmshWNHW0Y2FFfzjXPtp1LSS9bjsnMsQTuohp21nV")
					.header("Accept", "application/json")
					.asJson();
			JSONObject jsonObj = new JSONObject(response.getBody().toString());	
			if(jsonObj !=null)	
			{
				result = buildResponse(jsonObj);
			}
					
			
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result.toString();
	}	
	
	public static StringBuffer buildResponse(JSONObject jsonObj) {
		StringBuffer result = new StringBuffer();
		CouponData couponData ;
		try {
			JSONObject respObj = jsonObj.getJSONObject("response");
			JSONArray respArray = respObj.getJSONArray("data");
			for(int i = 0 ; i < respArray.length() ; i++){
				couponData = new CouponData();
				couponData.setTitle(respArray.getJSONObject(i).getString("title"));
				couponData.setDescription(respArray.getJSONObject(i).getString("description"));
				couponData.setStoreName(respArray.getJSONObject(i).getString("store_name"));
				couponData.setCouponCode(respArray.getJSONObject(i).getString("coupon_code"));
				   
				result.append("<b>"+couponData.getTitle()+"</b><br>"
						+ "<font size='4px;'>"+couponData.getDescription()+"</font><br>"
						+ "Merchant Name: <b>"+couponData.getStoreName()+"</b><br>"
						+ "Coupon Code: <b>"+couponData.getCouponCode()+"</b><br>"
								+ "***********************************<br><br>");
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
