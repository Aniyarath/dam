package com.igate.dam.common.framework.util;

import java.util.HashMap;
import java.util.Map;



public class ConstantUtil {

	/*Reference Type Codes*/
	//public static final String PHONE_TYPE_SECONDARY = "SECONDARY";
	//public static final String PHONE_TYPE_PRIMARY = "PRIMARY";	
	//Hardcoded Default values

	
	/*Formats, Special Characters, Regular Expressions */
	public static final String REGX_NUMBERS = "[0-9]*";
	public static final String REGX_ATRATE = "[@]";
	public static final String STAR = "*";
	public static final String EQUAL = "=";
	public static final String YEAR_FORMAT = "yyyy-MM-dd";
	public static final String EMPTY_STRING = "";

	
	public static final String APPROVAL_INDENTIFIER_DELIMITER = "-";
	
	
	public static final String OLD_VALUE_DELIMITER = " ";
	
	
	/** Constant used for the value for Y */
	public static final String YES = "Y";
	public static final String VM_KEY = "vmKey";
	public static final String ENTITY_PARTY_ADDRESS_NEW = "NEW";
	public static final Long DEFAULT_ADDRESS_ROLE_ID = 100l;
	public static final Object APPROVAL_STATUS_PENDING_FOR_DELETE = "";
	public static final String WPR = "WPR";
	public static final String TITLENAME = "TITLENAME";
	
	public static final Map<String,Long> STATUS_MAP = new HashMap<String,Long>(){
		{
			put("Save",1l);
			put("Submit",2l);
			put("Approve",3l);
			put("Reject",4l);
		}
	};
	public static final String REGIONAL_APPROVAL_PROCESS_NAME = "regionalapproval";
	public static final String INTIATE_FILM_ESTIMATE_WORKFLOW = "initiateestimate";
	
	
	
	public final static String VERSION_STATUS_NEW="NEW";
	public final static  String VERSION_STATUS_SUBMITTED="SUBMITTED";
	public final static  String VERSION_STATUS_APPROVED="APPROVED";
	public final static  String VERSION_STATUS_REJECTED ="REJECTED";
	
	//New , SUBMITTED FOR APPROVAL ,APPROVED .
	
	public final static  String VERSION_TYPE_INITIAL="INITIAL";
	public final static  String VERSION_TYPE_INPROGRESS="INPROGRESS";
	public final static  String VERSION_TYPE_ORIGINAL="ORIGINAL";
	public final static  String VERSION_TYPE_Final="Final";
	public final static  String VERSION_TYPE_INTERIM="INTERIM";
	public final static  String VERSION_TYPE_CURRENT="CURRENT";
	
	//Initial , In progress ,//Final 
	
	
	//Queries to fetch the estimates created by the territories
	
	public final static  String HO_WPR_ESTIMATE_QUERY = "select B.filmEstimateId"+
	",B.version"+
	",B.TerritoryID"+
	",B.territoryname"+
	",B.REGIONID"+
	",B.regionname"+
	",B.TITLEID"+
	",B.TITLENAME"+
	", sum(B.AdPub) as AdPub,sum(B.PrintCost) as PrintCost,sum(B.OtherDDE) as OtherDDE,sum(B.EstimateGBO) as EstimateGBO"+
	", (sum(B.AdPub)+sum(B.PrintCost)+sum(B.OtherDDE)+sum(B.EstimateGBO)) AS TotalCost "+
	"from ("+
	"select "+
	"A.filmEstimateId"+
	",A.version"+
	",A.totalCost"+
	",A.TerritoryID"+
	",A.territoryname"+
	",A.REGIONID"+
	",A.regionname"+
	",A.TITLEID"+
	",A.TITLENAME"+
	", if(A.estimateCostTypeId ='1',A.totalCost,0) AS AdPub"+
	", if(A.estimateCostTypeId ='2',A.totalCost,0) AS PrintCost"+
	", if(A.estimateCostTypeId ='3',A.totalCost,0) AS OtherDDE"+
	", if(A.estimateCostTypeId ='4',A.totalCost,0) AS EstimateGBO "+
	"from ("+
	"select "+
	" filmestimate.filmEstimateId"+
	",filmestimate.version"+
	",cost_category_estimate.estimateCostTypeId"+
	",cost_category_estimate.totalCost"+
	",territory.TerritoryID"+
	",territory.name AS territoryname"+
	",region.REGIONID"+
	",region.name AS regionname"+
	",title.TITLEID"+
	",title.TITLENAME "+
	"from filmestimate,cost_category_estimate,territory,region,title,"+
	"(select max(filmestimate.filmEstimateId) as filmEstimateId,filmestimate.territoryID,max(filmestimate.Version) AS Version1 from filmestimate,title "+
	"	where filmestimate.estimateStatus in('SUBMITTED','APPROVED') "+
	"	and filmestimate.estimateType in ('CURRENT','INTERIM') "+
	"	and filmestimate.titleID = title.TITLEID " +
	"	and upper(title.WPRNUMBER) like CONCAT('%',?,'%')" +
	"	group by filmestimate.territoryID"+
	") AS AA "+
	"where "+
	"filmestimate.filmEstimateId = cost_category_estimate.filmEstimateId and "+
	"filmestimate.territoryID = territory.TerritoryID and  "+
	"territory.RegionID = region.REGIONID and "+
	"filmestimate.titleID = title.TITLEID and  "+
	"filmestimate.filmEstimateId = AA.filmEstimateId "+
	"and filmestimate.territoryID = AA.territoryID "+
	"and filmestimate.Version = AA.Version1  "+
	"and filmestimate.titleID = title.TITLEID " +
	"and upper(title.WPRNUMBER) like CONCAT('%',?,'%')"+
	") AS A "+
	") AS B "+
	"group by B.filmEstimateId "+
	",B.version"+
	",B.TerritoryID"+
	",B.territoryname"+
	",B.REGIONID"+
	",B.regionname"+
	",B.TITLEID"+
	",B.TITLENAME";
	
	public final static  String HO_TITLENAME_ESTIMATE_QUERY = "select B.filmEstimateId"+
					",B.version"+
					",B.TerritoryID"+
					",B.territoryname"+
					",B.REGIONID"+
					",B.regionname"+
					",B.TITLEID"+
					",B.TITLENAME"+
					", sum(B.AdPub) as AdPub,sum(B.PrintCost) as PrintCost,sum(B.OtherDDE) as OtherDDE,sum(B.EstimateGBO) as EstimateGBO"+
					", (sum(B.AdPub)+sum(B.PrintCost)+sum(B.OtherDDE)+sum(B.EstimateGBO)) AS TotalCost "+
					"from ("+
					"select "+
					"A.filmEstimateId"+
					",A.version"+
					",A.totalCost"+
					",A.TerritoryID"+
					",A.territoryname"+
					",A.REGIONID"+
					",A.regionname"+
					",A.TITLEID"+
					",A.TITLENAME"+
					", if(A.estimateCostTypeId ='1',A.totalCost,0) AS AdPub"+
					", if(A.estimateCostTypeId ='2',A.totalCost,0) AS PrintCost"+
					", if(A.estimateCostTypeId ='3',A.totalCost,0) AS OtherDDE"+
					", if(A.estimateCostTypeId ='4',A.totalCost,0) AS EstimateGBO "+
					"from ("+
					"select "+
					" filmestimate.filmEstimateId"+
					",filmestimate.version"+
					",cost_category_estimate.estimateCostTypeId"+
					",cost_category_estimate.totalCost"+
					",territory.TerritoryID"+
					",territory.name AS territoryname"+
					",region.REGIONID"+
					",region.name AS regionname"+
					",title.TITLEID"+
					",title.TITLENAME "+
					"from filmestimate,cost_category_estimate,territory,region,title,"+
					"(select max(filmestimate.filmEstimateId) as filmEstimateId,filmestimate.territoryID,max(filmestimate.Version) AS Version1 from filmestimate,title "+
					"	where filmestimate.estimateStatus in('SUBMITTED','APPROVED') "+
					"	and filmestimate.estimateType in ('CURRENT','INTERIM') "+
					"	and filmestimate.titleID = title.TITLEID " +
					"	and upper(title.TITLENAME) like CONCAT('%',?,'%')" +
					"	group by filmestimate.territoryID"+
					") AS AA "+
					"where "+
					"filmestimate.filmEstimateId = cost_category_estimate.filmEstimateId and "+
					"filmestimate.territoryID = territory.TerritoryID and  "+
					"territory.RegionID = region.REGIONID and "+
					"filmestimate.titleID = title.TITLEID and  "+
					"filmestimate.filmEstimateId = AA.filmEstimateId "+
					"and filmestimate.territoryID = AA.territoryID "+
					"and filmestimate.Version = AA.Version1  "+
					"and filmestimate.titleID = title.TITLEID " +
					"and upper(title.TITLENAME) like CONCAT('%',?,'%')"+
					") AS A "+
					") AS B "+
					"group by B.filmEstimateId "+
					",B.version"+
					",B.TerritoryID"+
					",B.territoryname"+
					",B.REGIONID"+
					",B.regionname"+
					",B.TITLEID"+
					",B.TITLENAME";
	
	public final static  String HO_ESTIMATE_QUERY = "select B.filmEstimateId"+
				",B.version"+
				",B.TerritoryID"+
				",B.territoryname"+
				",B.REGIONID"+
				",B.regionname"+
				",B.TITLEID"+
				",B.TITLENAME"+
				", sum(B.AdPub) as AdPub,sum(B.PrintCost) as PrintCost,sum(B.OtherDDE) as OtherDDE,sum(B.EstimateGBO) as EstimateGBO"+
				", (sum(B.AdPub)+sum(B.PrintCost)+sum(B.OtherDDE)+sum(B.EstimateGBO)) AS TotalCost "+
				"from ("+
				"select "+
				"A.filmEstimateId"+
				",A.version"+
				",A.totalCost"+
				",A.TerritoryID"+
				",A.territoryname"+
				",A.REGIONID"+
				",A.regionname"+
				",A.TITLEID"+
				",A.TITLENAME"+
				", if(A.estimateCostTypeId ='1',A.totalCost,0) AS AdPub"+
				", if(A.estimateCostTypeId ='2',A.totalCost,0) AS PrintCost"+
				", if(A.estimateCostTypeId ='3',A.totalCost,0) AS OtherDDE"+
				", if(A.estimateCostTypeId ='4',A.totalCost,0) AS EstimateGBO "+
				"from ("+
				"select "+
				" filmestimate.filmEstimateId"+
				",filmestimate.version"+
				",cost_category_estimate.estimateCostTypeId"+
				",cost_category_estimate.totalCost"+
				",territory.TerritoryID"+
				",territory.name AS territoryname"+
				",region.REGIONID"+
				",region.name AS regionname"+
				",title.TITLEID"+
				",title.TITLENAME "+
				"from filmestimate,cost_category_estimate,territory,region,title,"+
				"(select max(filmestimate.filmEstimateId) as filmEstimateId,filmestimate.territoryID,max(filmestimate.Version) AS Version1 from filmestimate "+
				"	where filmestimate.estimateStatus in('SUBMITTED','APPROVED') "+
				"	and filmestimate.estimateType in('CURRENT','INTERIM') "+
				"	and filmestimate.titleID = ? " +
				"	group by filmestimate.territoryID"+
				") AS AA "+
				"where "+
				"filmestimate.filmEstimateId = cost_category_estimate.filmEstimateId and "+
				"filmestimate.territoryID = territory.TerritoryID and  "+
				"territory.RegionID = region.REGIONID and "+
				"filmestimate.titleID = title.TITLEID and  "+
				"filmestimate.filmEstimateId = AA.filmEstimateId "+
				"and filmestimate.territoryID = AA.territoryID "+
				"and filmestimate.Version = AA.Version1  "+
				"and filmestimate.titleID = ? "+
				") AS A "+
				") AS B "+
				"group by B.filmEstimateId "+
				",B.version"+
				",B.TerritoryID"+
				",B.territoryname"+
				",B.REGIONID"+
				",B.regionname"+
				",B.TITLEID"+
				",B.TITLENAME";
	
	public final static  String REGION_WPR_ESTIMATE_QUERY = "select B.filmEstimateId"+
					",B.version"+
					",B.TerritoryID"+
					",B.territoryname"+
					",B.REGIONID"+
					",B.regionname"+
					",B.TITLEID"+
					",B.TITLENAME"+
					", sum(B.AdPub) as AdPub,sum(B.PrintCost) as PrintCost,sum(B.OtherDDE) as OtherDDE,sum(B.EstimateGBO) as EstimateGBO"+
					", (sum(B.AdPub)+sum(B.PrintCost)+sum(B.OtherDDE)+sum(B.EstimateGBO)) AS TotalCost "+
					"from ("+
					"select "+
					"A.filmEstimateId"+
					",A.version"+
					",A.totalCost"+
					",A.TerritoryID"+
					",A.territoryname"+
					",A.REGIONID"+
					",A.regionname"+
					",A.TITLEID"+
					",A.TITLENAME"+
					", if(A.estimateCostTypeId ='1',A.totalCost,0) AS AdPub"+
					", if(A.estimateCostTypeId ='2',A.totalCost,0) AS PrintCost"+
					", if(A.estimateCostTypeId ='3',A.totalCost,0) AS OtherDDE"+
					", if(A.estimateCostTypeId ='4',A.totalCost,0) AS EstimateGBO "+
					"from ("+
					"select "+
					" filmestimate.filmEstimateId"+
					",filmestimate.version"+
					",cost_category_estimate.estimateCostTypeId"+
					",cost_category_estimate.totalCost"+
					",territory.TerritoryID"+
					",territory.name AS territoryname"+
					",region.REGIONID"+
					",region.name AS regionname"+
					",title.TITLEID"+
					",title.TITLENAME "+
					"from filmestimate,cost_category_estimate,territory,region,title,"+
					"(select max(filmestimate.filmEstimateId) as filmEstimateId,filmestimate.territoryID,max(filmestimate.Version) AS Version1 from filmestimate,title "+
					"	where filmestimate.estimateStatus in('SUBMITTED','APPROVED') "+
					"	and filmestimate.estimateType in('CURRENT','INTERIM') "+
					"	and filmestimate.titleID = title.TITLEID " +
					"	and upper(title.WPRNUMBER) like CONCAT('%',?,'%') " +
					" and filmestimate.territoryID in (select territory.TerritoryID from territory where territory.RegionID = ?)" + 
					"	group by filmestimate.territoryID"+
					") AS AA "+
					"where "+
					"filmestimate.filmEstimateId = cost_category_estimate.filmEstimateId and "+
					"filmestimate.territoryID = territory.TerritoryID and  "+
					"territory.RegionID = region.REGIONID and "+
					"filmestimate.titleID = title.TITLEID and  "+
					"filmestimate.filmEstimateId = AA.filmEstimateId "+
					"and filmestimate.territoryID = AA.territoryID "+
					"and filmestimate.Version = AA.Version1  "+
					"and filmestimate.titleID = title.TITLEID  " +
					"and upper(title.WPRNUMBER) like CONCAT('%',?,'%') "+
					") AS A "+
					") AS B "+
					"group by B.filmEstimateId "+
					",B.version"+
					",B.TerritoryID"+
					",B.territoryname"+
					",B.TITLEID"+
					",B.TITLENAME";
	
	public final static  String REGION_TITLENAME_ESTIMATE_QUERY = "select B.filmEstimateId"+
					",B.version"+
					",B.TerritoryID"+
					",B.territoryname"+
					",B.REGIONID"+
					",B.regionname"+
					",B.TITLEID"+
					",B.TITLENAME"+
					", sum(B.AdPub) as AdPub,sum(B.PrintCost) as PrintCost,sum(B.OtherDDE) as OtherDDE,sum(B.EstimateGBO) as EstimateGBO"+
					", (sum(B.AdPub)+sum(B.PrintCost)+sum(B.OtherDDE)+sum(B.EstimateGBO)) AS TotalCost "+
					"from ("+
					"select "+
					"A.filmEstimateId"+
					",A.version"+
					",A.totalCost"+
					",A.TerritoryID"+
					",A.territoryname"+
					",A.REGIONID"+
					",A.regionname"+
					",A.TITLEID"+
					",A.TITLENAME"+
					", if(A.estimateCostTypeId ='1',A.totalCost,0) AS AdPub"+
					", if(A.estimateCostTypeId ='2',A.totalCost,0) AS PrintCost"+
					", if(A.estimateCostTypeId ='3',A.totalCost,0) AS OtherDDE"+
					", if(A.estimateCostTypeId ='4',A.totalCost,0) AS EstimateGBO "+
					"from ("+
					"select "+
					" filmestimate.filmEstimateId"+
					",filmestimate.version"+
					",cost_category_estimate.estimateCostTypeId"+
					",cost_category_estimate.totalCost"+
					",territory.TerritoryID"+
					",territory.name AS territoryname"+
					",region.REGIONID"+
					",region.name AS regionname"+
					",title.TITLEID"+
					",title.TITLENAME "+
					"from filmestimate,cost_category_estimate,territory,region,title,"+
					"(select max(filmestimate.filmEstimateId) as filmEstimateId,filmestimate.territoryID,max(filmestimate.Version) AS Version1 from filmestimate,title "+
					"	where filmestimate.estimateStatus in('SUBMITTED','APPROVED') "+
					"	and filmestimate.estimateType in('CURRENT','INTERIM') "+
					"	and filmestimate.titleID = title.TITLEID " +
					"	and upper(title.TITLENAME) like CONCAT('%',?,'%') " +
					" and filmestimate.territoryID in (select territory.TerritoryID from territory where territory.RegionID = ?)" + 
					"	group by filmestimate.territoryID"+
					") AS AA "+
					"where "+
					"filmestimate.filmEstimateId = cost_category_estimate.filmEstimateId and "+
					"filmestimate.territoryID = territory.TerritoryID and  "+
					"territory.RegionID = region.REGIONID and "+
					"filmestimate.titleID = title.TITLEID and  "+
					"filmestimate.filmEstimateId = AA.filmEstimateId "+
					"and filmestimate.territoryID = AA.territoryID "+
					"and filmestimate.Version = AA.Version1  "+
					"and filmestimate.titleID = title.TITLEID  " +
					"and upper(title.TITLENAME) like CONCAT('%',?,'%') "+
					") AS A "+
					") AS B "+
					"group by B.filmEstimateId "+
					",B.version"+
					",B.TerritoryID"+
					",B.territoryname"+
					",B.TITLEID"+
					",B.TITLENAME";
	
	//Initial Query without region
	
	
	/*public final static  String REGION_ESTIMATE_QUERY = "select B.filmEstimateId"+
					",B.version"+
					",B.TerritoryID"+
					",B.territoryname"+
					",B.TITLEID"+
					",B.TITLENAME"+
					", sum(B.AdPub) as AdPub,sum(B.PrintCost) as PrintCost,sum(B.OtherDDE) as OtherDDE,sum(B.EstimateGBO) as EstimateGBO"+
					", (sum(B.AdPub)+sum(B.PrintCost)+sum(B.OtherDDE)+sum(B.EstimateGBO)) AS TotalCost "+
					"from ("+
					"select "+
					"A.filmEstimateId"+
					",A.version"+
					",A.totalCost"+
					",A.TerritoryID"+
					",A.territoryname"+
					",A.TITLEID"+
					",A.TITLENAME"+
					", if(A.estimateCostTypeId ='1',A.totalCost,0) AS AdPub"+
					", if(A.estimateCostTypeId ='2',A.totalCost,0) AS PrintCost"+
					", if(A.estimateCostTypeId ='3',A.totalCost,0) AS OtherDDE"+
					", if(A.estimateCostTypeId ='4',A.totalCost,0) AS EstimateGBO "+
					"from ("+
					"select "+
					" filmestimate.filmEstimateId"+
					",filmestimate.version"+
					",cost_category_estimate.estimateCostTypeId"+
					",cost_category_estimate.totalCost"+
					",territory.TerritoryID"+
					",territory.name AS territoryname"+
					",title.TITLEID"+
					",title.TITLENAME "+
					"from filmestimate,cost_category_estimate,territory,region,title,"+
					"(select filmestimate.filmEstimateId,filmestimate.territoryID,max(filmestimate.Version) AS Version1 from filmestimate "+
					"	where filmestimate.estimateStatus in('SUBMITTED','APPROVED') "+
					"	and filmestimate.estimateType = 'CURRENT' "+
					"	and filmestimate.titleID = ? " +
					" and filmestimate.territoryID in (select territory.TerritoryID from territory where territory.RegionID = ?)" + 
					"	group by filmestimate.filmEstimateId,filmestimate.territoryID"+
					") AS AA "+
					"where "+
					"filmestimate.filmEstimateId = cost_category_estimate.filmEstimateId and "+
					"filmestimate.territoryID = territory.TerritoryID and  "+
					"territory.RegionID = region.REGIONID and "+
					"filmestimate.titleID = title.TITLEID and  "+
					"filmestimate.filmEstimateId = AA.filmEstimateId "+
					"and filmestimate.territoryID = AA.territoryID "+
					"and filmestimate.Version = AA.Version1  "+
					"and filmestimate.titleID = ? "+
					") AS A "+
					") AS B "+
					"group by B.filmEstimateId "+
					",B.version"+
					",B.TerritoryID"+
					",B.territoryname"+
					",B.TITLEID"+
					",B.TITLENAME";*/
	
//Query with Region
	
	
	public final static  String REGION_ESTIMATE_QUERY = "select B.filmEstimateId"+
	",B.version"+
	",B.TerritoryID"+
	",B.territoryname"+
	",B.REGIONID"+
	",B.regionname"+
	",B.TITLEID"+
	",B.TITLENAME"+
	", sum(B.AdPub) as AdPub,sum(B.PrintCost) as PrintCost,sum(B.OtherDDE) as OtherDDE,sum(B.EstimateGBO) as EstimateGBO"+
	", (sum(B.AdPub)+sum(B.PrintCost)+sum(B.OtherDDE)+sum(B.EstimateGBO)) AS TotalCost "+
	"from ("+
	"select "+
	"A.filmEstimateId"+
	",A.version"+
	",A.totalCost"+
	",A.TerritoryID"+
	",A.territoryname"+
	",A.REGIONID"+
	",A.regionname"+
	",A.TITLEID"+
	",A.TITLENAME"+
	", if(A.estimateCostTypeId ='1',A.totalCost,0) AS AdPub"+
	", if(A.estimateCostTypeId ='2',A.totalCost,0) AS PrintCost"+
	", if(A.estimateCostTypeId ='3',A.totalCost,0) AS OtherDDE"+
	", if(A.estimateCostTypeId ='4',A.totalCost,0) AS EstimateGBO "+
	"from ("+
	"select "+
	" filmestimate.filmEstimateId"+
	",filmestimate.version"+
	",cost_category_estimate.estimateCostTypeId"+
	",cost_category_estimate.totalCost"+
	",territory.TerritoryID"+
	",territory.name AS territoryname"+
	",region.REGIONID"+
	",region.name AS regionname"+
	",title.TITLEID"+
	",title.TITLENAME "+
	"from filmestimate,cost_category_estimate,territory,region,title,"+
	"(select max(filmestimate.filmEstimateId) as filmEstimateId,filmestimate.territoryID,max(filmestimate.Version) AS Version1 from filmestimate "+
	"	where filmestimate.estimateStatus in('SUBMITTED','APPROVED') "+
	"	and filmestimate.estimateType in('CURRENT','INTERIM') "+
	"	and filmestimate.titleID = ? " +
	" and filmestimate.territoryID in (select territory.TerritoryID from territory where territory.RegionID = ?)" + 
	"	group by filmestimate.territoryID"+
	") AS AA "+
	"where "+
	"filmestimate.filmEstimateId = cost_category_estimate.filmEstimateId and "+
	"filmestimate.territoryID = territory.TerritoryID and  "+
	"territory.RegionID = region.REGIONID and "+
	"filmestimate.titleID = title.TITLEID and  "+
	"filmestimate.filmEstimateId = AA.filmEstimateId "+
	"and filmestimate.territoryID = AA.territoryID "+
	"and filmestimate.Version = AA.Version1  "+
	"and filmestimate.titleID = ? "+
	") AS A "+
	") AS B "+
	"group by B.filmEstimateId "+
	",B.version"+
	",B.TerritoryID"+
	",B.territoryname"+
	",B.TITLEID"+
	",B.TITLENAME";
	
	
	public final static  String TERRITORY_RECENT_ESTIMATE_QUERY = "select * from  "+
			"("+
			"select filmestimate.filmEstimateId as 'filmestimateid',title.TITLEID,title.TITLENAME,"+
			" filmestimate.version,filmestimate.estimateStatus,filmestimate.estimateType "+
			"from filmestimate,title "+
			"where filmestimate.territoryID = ? "+
			" 	and filmestimate.titleID = title.TITLEID "+
			" 	and filmestimate.estimateStatus in ('SUBMITTED','APPROVED') "+
			" 	and filmestimate.estimateType in('CURRENT','INTERIM') "+
			" 	order by "+
			" 	 filmestimate.filmEstimateId desc,"+
			"	 filmestimate.version desc  "+
			") AA "+
			"limit 1";
	
	public final static  String REGION_RECENT_ESTIMATE_QUERY = "select * from  "+
	"("+
	"select filmestimate.filmEstimateId as 'filmestimateid',title.TITLEID,title.TITLENAME,"+
	" filmestimate.version,filmestimate.estimateStatus,filmestimate.estimateType "+
	"from filmestimate,title,territory,region "+
	"where filmestimate.territoryID = territory.TerritoryID " +
	"and territory.RegionID = region.REGIONID "+
	" 	and filmestimate.titleID = title.TITLEID "+
	" 	and filmestimate.estimateStatus in ('SUBMITTED','APPROVED') "+
	" 	and filmestimate.estimateType in('CURRENT','INTERIM') " +
	"and region.REGIONID = ?"+
	" 	order by "+
	" 	 filmestimate.filmEstimateId desc,"+
	"	 filmestimate.version desc  "+
	") AA "+
	"limit 1";
	
	public final static  String HO_RECENT_ESTIMATE_QUERY = "select * from  "+
	"("+
	"select filmestimate.filmEstimateId as 'filmestimateid',title.TITLEID,title.TITLENAME,"+
	" filmestimate.version,filmestimate.estimateStatus,filmestimate.estimateType "+
	"from filmestimate,title,territory,region,headoffice "+
	"where filmestimate.territoryID = territory.TerritoryID " +
	"and territory.RegionID = region.REGIONID " +
	"and region.HOID = headoffice.HOID "+
	" 	and filmestimate.titleID = title.TITLEID "+
	" 	and filmestimate.estimateStatus in ('SUBMITTED','APPROVED') "+
	" 	and filmestimate.estimateType in('CURRENT','INTERIM') " +
	"and headoffice.HOID = ? " +
	" 	order by "+
	" 	 filmestimate.filmEstimateId desc,"+
	"	 filmestimate.version desc  "+
	") AA "+
	"limit 1";
	
	public final static String TERRIOTRY_TITLE_SEARCH_QUERY = "select title.TITLEID," +
	"title.TITLENAME,title.WPRNUMBER,title.PRODUCTNUMBER,"+
	"title.MPANUMBER,title.VIDEOCATELOG,title.VISA,title.EDICODE,"+
	"title.RELEASE_DATE,title.COUNTRYID,"+
	"title.HH,title.MM,title.SS,title.ASPECTRATIO,title.LANGUAGEID,"+
	"title.ACTIVEFLAG,title.RIGHTSNOTIFICATION,title.COMMENTS,"+
	"title.CREATEDBY,title.LASTUPDATEDBY,title.CREATEDDATETIME,title.LASTUPDATEDDATETIME,"+
	"title.DELETEDFLAG,"+
	"if(film.titleID is null,'Create Initial Estimate','Modify')  as 'Activity' "+
	"from title left outer join  "+
	"(select distinct filmestimate.titleID from filmestimate  "+
	"where filmestimate.territoryID = ?) film "+
	"on title.TITLEID = film.titleID " +
	"where title.DELETEDFLAG != 'Y'  ";
	
	
	
	public final static String RECENT_ESTIMATE_TITLE_QUERY = "select title.TITLEID," +
	"title.TITLENAME,title.WPRNUMBER,title.PRODUCTNUMBER,"+
	"title.MPANUMBER,title.VIDEOCATELOG,title.VISA,title.EDICODE,"+
	"title.RELEASE_DATE,title.COUNTRYID,"+
	"title.HH,title.MM,title.SS,title.ASPECTRATIO,title.LANGUAGEID,"+
	"title.ACTIVEFLAG,title.RIGHTSNOTIFICATION,title.COMMENTS,"+
	"title.CREATEDBY,title.LASTUPDATEDBY,title.CREATEDDATETIME,title.LASTUPDATEDDATETIME,"+
	"title.DELETEDFLAG "+
	"from title "+
	"where title.TITLEID in ";
	
	public final static String REGION_TO_INITIATE_ESTIMATE_QUERY = "select A.territoryID,A.name,if(b.territoryID is null,'N','Y') AS FL from  "+
	"(select territoryID,name from territory where regionid = ?) AS A "+
	"left join (select distinct titleid, territoryID from filmestimate  "+
	"where titleID is not null and titleID = ? ) AS B "+
	"ON A.territoryID = B.territoryID ";
}
