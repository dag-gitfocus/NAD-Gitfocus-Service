package com.gitfocus.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gitfocus.git.db.model.Units;
import com.gitfocus.repository.UnitsRepository;

/**
 * 
 * @author Tech Mahindra 
 * Common utility class for GitFocus-Service Application
 * 
 */
@Service
public class GitFocusUtil {

	private static final Logger logger = LoggerFactory.getLogger(GitFocusUtil.class.getSimpleName());

	public GitFocusUtil() {
		super();
		logger.info("GitFocusUtil init");
	}

	@Autowired
	private UnitsRepository uRepository;

	String accessToken = null;
	RestTemplate restTemplate = null;
	ResponseEntity<String> jsonResponse = null;
	HttpHeaders headers = null;
	HttpEntity<String> entity = null;

	/**
	 * Method to add AcceessToken in HTTPHeader
	 * 
	 * @param jsonURL
	 * @return jsonResponse
	 */
	public String getGitAPIJsonResponse(String jsonURL) {
		logger.info("getGitAPIJsonResponse - Adding AccessToken with jsonURL");
		List<Units> units = uRepository.findAll();
		units.forEach(response -> {
			if (response.getUnitName().equalsIgnoreCase("TR")) {
				accessToken = response.getAccessToken();
				headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				headers.set("Authorization", "Bearer " + accessToken);
				entity = new HttpEntity<String>(headers);
				restTemplate = new RestTemplate();
				jsonResponse = restTemplate.exchange(jsonURL, HttpMethod.GET, entity, String.class);
			}
		});
		return jsonResponse.getBody();

	}

	/**
	 * Method to convert String to Date
	 * 
	 * @param date
	 * @return sDate
	 * @throws ParseException
	 */
	public static Date stringToDate(String date) {
		logger.info("stringToDate - Date is  " + date);
		Timestamp timestamp = null;
		Date parsedDate = null;
		try  {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
			dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
			parsedDate = dateFormat.parse(date);
			timestamp = new java.sql.Timestamp(parsedDate.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return timestamp;
	}

	/**
	 * Method to get start and endDate
	 * 
	 * @param date
	 * @return Date[]
	 */
	public static Date[] getStartAndEndDate(String date) {
		logger.info("getStartAndEndDate - Date is  " + date);
		Date startDate = null;
		Date endDate = null;
		String newDate = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
			startDate = sdf.parse(date);

			Calendar c = Calendar.getInstance();
			c.setTime(sdf.parse(date));
			c.add(Calendar.DAY_OF_MONTH, 1);
			newDate = sdf.format(c.getTime());
			endDate = sdf.parse(newDate);

		} catch (ParseException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		return new Date[] { startDate, endDate };
	}

	/**
	 * Method to convert Date to String
	 * 
	 * @param indate
	 * @return dateString
	 */
	public static String convertDateToString(Date date) {
		logger.info("convertStringToDate - Date is  " + date);
		String dateString = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		try {
			dateString = sdf.format(date);
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		return dateString;
	}

	/**
	 * Method to calculteDaysBetweenTwoDates or Hours between Two Dates
	 * 
	 * @param createdDate
	 * @param reviewedDate
	 * @return calculteDaysBetweenTwoDatesOrHours
	 */
	public static List<Entry<Long, String>> calculteDaysBetweenTwoDatesOrHours(String createdDate, String reviewedDate) {
		logger.info("calculteDaysBetweenTwoDates - Date is  " + createdDate, reviewedDate);

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.US);
		long noOfDaysBetweenDatesOrHours = 0;
		long hoursBetweenDates = 0;
		long miniutesBetweenDates = 0;
		boolean sameDay = false;
		
		Map<Long, String> noOfDaysBetweenDatesOrHoursMap = new HashMap<Long, String>();
		LocalDateTime d1 = LocalDateTime.parse(createdDate.substring(0, 19), dtf);
		LocalDateTime d2 = LocalDateTime.parse(reviewedDate.substring(0, 19), dtf);
		sameDay = createdDate.substring(0, 10).equals(reviewedDate.substring(0, 10));

		// if createdDate and reviewedDate dates are same day then calculate hours
		if (sameDay == true) {
			hoursBetweenDates = ChronoUnit.HOURS.between(d1, d2);
			// get hours between createdDate and reviewedDate
			if(hoursBetweenDates > 1) {
				noOfDaysBetweenDatesOrHoursMap.put(hoursBetweenDates, "Hours");
			// get minutes between createdDate and reviewedDate	
			} else if (hoursBetweenDates == 0 && hoursBetweenDates < 1 ) {
				miniutesBetweenDates = ChronoUnit.MINUTES.between(d1, d2);
				noOfDaysBetweenDatesOrHoursMap.put(miniutesBetweenDates, "Miniutes");
			}
		// if createdDate and reviewedDate dates aren't same day then calculate days
		} else {
			noOfDaysBetweenDatesOrHours = ChronoUnit.DAYS.between(d1, d2);
			noOfDaysBetweenDatesOrHoursMap.put(noOfDaysBetweenDatesOrHours, "Days");
		}

		return noOfDaysBetweenDatesOrHoursMap.entrySet().stream().collect(Collectors.toList());
	}
}
