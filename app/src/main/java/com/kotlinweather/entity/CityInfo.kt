package com.kotlinweather.entity

/**
 * Project Name：KotlinWeather
 * Description ：用于取出记录csv文件中的信息
 * @author     ：ZouWeiLin on 2019.03.04
 */
class CityInfo {

    public constructor()
    public constructor(City_EN: String, City_CN: String) {
        this.City_EN = City_EN
        this.City_CN = City_CN
    }

    /**
     * CN101010100
     */
    var City_ID = ""
    /**
     * 拼音：beijing
     */
    var City_EN = ""
    /**
     * 北京
     */
    var City_CN = ""
    /**
     * CN
     */
    var Country_code = ""
    /**
     * China
     */
    var Country_EN = ""
    /**
     * 中国
     */
    var Country_CN = ""
    /**
     * beijing
     */
    var Province_EN = ""
    /**
     * 北京
     */
    var Province_CN = ""
    /**
     * 省会/首府：beijing
     */
    var Admin_district_EN = ""
    /**
     * 省会/首府：北京
     */
    var Admin_district_CN = ""
    /**
     * 纬度：39.904987
     */
    var Latitude: Double = 0.0
    /**
     * 经度：116.40529
     */
    var Longitude: Double = 0.0
    /**
     * 邮编：110108
     */
    var AD_code = ""

    override fun toString(): String {
        return "CityInfo(City_ID=$City_ID, City_EN=$City_EN, City_CN=$City_CN, Country_code=$Country_code, Country_EN=$Country_EN, Country_CN=$Country_CN, Province_EN=$Province_EN, Province_CN=$Province_CN, Admin_district_EN=$Admin_district_EN, Admin_district_CN=$Admin_district_CN, Latitude=$Latitude, Longitude=$Longitude, AD_code=$AD_code)"
    }


}