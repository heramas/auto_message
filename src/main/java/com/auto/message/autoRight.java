package com.auto.message;

import lombok.Getter;

@Getter
public enum autoRight {
    /*
    PC,MO 모두 계약되어있으며,
    재다운로드를 포함한 필터링 로그를 전달해야함
    */
    SY_MEDIA(		"에스와이미디어"	, 2618119, "ALL", "ALL", "Y", "Y"),
    MEDIA_ELITE(	"미디어엘리트"		, 2618120, "ALL", "ALL", "Y", "Y"),
    COCOMIX(		"코코믹스"			, 2618123, "ALL", "ALL", "Y", "Y"),
    ONBIZNET_VR(	"온비즈넷_VR"		, 2618126, "ALL", "ALL", "N", "Y"),
    ONBIZNET_MOVIE(	"온비즈넷_영화"		, 2618044, "ALL", "ALL", "N", "Y"),
    ONBIZNET_DRAMA(	"온비즈넷_드라마"	, 2618032, "ALL", "ALL", "N", "Y"),
    REDUNDER_MEDIA(	"레드언더미디어"	, 2618080, "ALL", "ALL", "N", "Y"),
    UBNETWORK(		"유비네트워크"		, 2618053, "ALL", "ALL", "N", "Y"),
    SMAILE_CONTENTS("스마일컨텐츠"		, 2618109, "ALL", "ALL", "N", "Y"),
    CONTENTS_WING(	"콘텐츠윙"			, 2618113, "ALL", "ALL", "Y", "Y"),
    KTH(			"KTH"			, 2617430, "ALL", "ALL", "N", "Y"),
    KTH_SKYTV(		"KTH_SKYTV"		, 2618116, "ALL", "ALL", "N", "Y"),
    MICON(			"미콘"			, 2617978, "ALL", "ALL", "Y", "Y"),

    // 필터링키 추가.
    JAYE_MBN(		"JAYE_MBN"		, 2618127, "ALL", "ALL", "N", "Y"),
    JAYE_TVCHOSUN(	"JAYE_TV조선"		, 2618114, "ALL", "ALL", "N", "Y"),
    JAYE_CHA(		"JAYE_채널A"		, 2618112, "ALL", "ALL", "N", "Y"),
    JAYE_CU(		"JAYE_CU"		, 2618071, "ALL", "ALL", "N", "Y"),
    JAYE_MOVIE(		"JAYE_MOVIE"	, 2618003, "ALL", "ALL", "N", "Y"),
    JAYE_ICONIX(	"JAYE_아이코닉스"	, 2618128, "ALL", "ALL", "N", "Y"),
    JAYE_SHOWBOX(	"JAYE_SHOWBOX"	, 2618130, "ALL", "ALL", "N", "Y"),

    /*
    PC,MO 모두 계약되어있으며,
    재다운로드를 제외한 필터링 로그를 전달해야함
    */
    KBS(				"KBS"				, 2615557, "Y", "ALL", "N", "N"),
    CONTENT_PLAY(		"콘텐츠플레이"			, 2618136, "Y", "ALL", "N", "N"),
    ROON_COMPANY(		"룬컴퍼니"				, 2617987, "Y", "ALL", "Y", "Y"),
    WAG(				"더블유에이지"			, 2618105, "Y", "ALL", "N", "Y"),
    APEX_MEIDA(			"에이펙스미디어"		, 2618029, "Y", "ALL", "N", "Y"),
    BUCKETSTUDIO(		"버킷스튜디오"			, 2617976, "Y", "ALL", "N", "Y"),
    BUCKETSTUDIO_TCC(	"버킷스튜디오_더컨텐츠콤"	, 2618011, "Y", "ALL", "N", "Y"),
    RAONCOMPANY_PLUS(	"라온컴퍼니플러스"		, 2618102, "Y", "ALL", "N", "Y"),
    UNDER_SHIELD(		"언더쉴드"				, 2618137, "Y", "ALL", "Y", "Y"),
    PICTURESQUE(		"픽쳐레스크"			, 2618122, "Y", "ALL", "N", "N"),
    PICTURESQUE_EBS(	"픽쳐레스크_EBS"		, 2615760, "Y", "ALL", "N", "N"),
    CJTV(				"CJTV"				, 2618099, "Y", "ALL", "N", "N"),
    CJ(					"CJ"				, 2618010, "Y", "ALL", "N", "N"),
    iMBC(				"iMBC"				, 2615595, "Y", "ALL", "N", "N"),
    /*
    PC만 계약되어있으며,
    재다운로드를 포함한 필터링 로그를 전달해야함

    SBS - ALL로 수정 - 20.03.10
    */
    SBS(				"SBS"		, 2615596, "ALL", "ALL", "N", "N"),
    SBS_CABLE(			"SBS케이블"	, 2618124, "ALL", "ALL", "N", "N"),

    /*
    PC만 계약되어있으며,
    재다운로드를 제외한 필터링 로그를 전달해야함
    */
    CONTENTSLOAD_JTBC(		"컨텐츠로드_JTBC"		, 2618019, "Y", "PC", "N", "N"),
    CONTENTSLOAD_MOVIE(		"컨텐츠로드_영화"		, 2617793, "Y", "PC", "N", "N"),
    CONTENTSLOAD_SBS_CABLE(	"컨텐츠로드_SBS케이블"	, 2618134, "Y", "PC", "N", "N");

    private String rightName;
    private int right_id;
    private String pay_yn; //재다운로드 포함 여부
    private String device; //장치구분
    private String block_yn; // 차단로그 추출 여부 (7개권리사만 추출함)
    private String rightCheck;
    
    autoRight(String rightName, int right_id, String pay_yn, String device, String block_yn, String rightCheck) {
        this.rightName = rightName;
        this.right_id = right_id;
        this.pay_yn = pay_yn;
        this.device = device;
        this.block_yn = block_yn;
        this.rightCheck = rightCheck; // Y: 필터링+빌링 / N : 빌링로그만
    }
}

