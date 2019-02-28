package com.ctsreporting.reportGenerator.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctsreporting.reportGenerator.model.Associate;
import com.ctsreporting.reportGenerator.model.Event;
import com.ctsreporting.reportGenerator.model.EventDetails;
import com.ctsreporting.reportGenerator.repository.AssociateRepository;
import com.ctsreporting.reportGenerator.repository.EventDetailsRepository;
import com.ctsreporting.reportGenerator.repository.EventRepository;

@Service
public class ProcessDataService {
	
	private static String associateDetailsFileName = "AssociateDetails.xls";
	private static String eventSummaryFileName = "EventsSummary.xls";
	private static String eventImformationFileName = "EventImformation";
	
	@Autowired
	private AssociateRepository associateRepository;
	
	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private EventDetailsRepository eventDetailsRepository;
	
	public void processUpdatedExcels() {
	    saveFile(associateDetailsFileName);
	    saveFile(eventSummaryFileName);
	    saveFile(eventImformationFileName);
	}
	
	public void saveFile(String filename) {
		Workbook workbook = null;
		try {
			File associateDetailsFile =    new File(WatchFolderService.FOLDER_TO_MONITOR+"/"+filename);
			workbook = WorkbookFactory.create(associateDetailsFile);
			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rows =sheet.iterator();
			if (filename.equals(associateDetailsFileName)) {
				createAssociate(rows);
			}else if (filename.equals(eventSummaryFileName)) {
				CreateEventSummary(rows);
			}else if (filename.equals(eventImformationFileName)) {
				CreateEventSummary(rows);
			}
		} catch (EncryptedDocumentException | IOException e) {
			e.printStackTrace();
		}finally {
			try {
				workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void createEventDetails(Iterator<Row> rows) {
		boolean isFirstRow = true;
		while(rows.hasNext()) {
			if (isFirstRow) {
				isFirstRow = false;
				rows.next();
			}else {
				Event event = new Event();
				Associate associate = new Associate();
				EventDetails eventDetails = new EventDetails();
				Iterator<Cell> cellsIterator = rows.next().iterator();
				List<Cell> cellList = new ArrayList<>();
				cellsIterator.forEachRemaining(cellList::add);
				String eventId = cellList.get(0).getStringCellValue();
				Long associateId = (long)cellList.get(7).getNumericCellValue();
				event = eventRepository.findByEventId(eventId);
				associate = associateRepository.findByAssociateId(associateId);
				EventDetails eventDetailsInDb = eventDetailsRepository.findAssociateIdAndEventId(associateId, eventId);
				if (eventDetailsInDb != null) {
					eventDetails.setId(eventDetailsInDb.getId());
				}
				eventDetails.setVolunteerHours((long)cellList.get(9).getNumericCellValue());
				eventDetails.setTravelHours((long)cellList.get(10).getNumericCellValue());
				eventDetails.setAssociate(associate);
				eventDetails.setEvent(event);
			}
		}
	}
	
	public void CreateEventSummary(Iterator<Row> rows) {
		boolean isFirstRow = true;
		
		while(rows.hasNext()) {
			if (isFirstRow) {
				isFirstRow = false;
				rows.next();
			}else {
				Event event = new Event();
				Iterator<Cell> cellsIterator = rows.next().iterator();
				List<Cell> cellList = new ArrayList<>();
				cellsIterator.forEachRemaining(cellList::add);
				String eventId = cellList.get(0).getStringCellValue();
				Event eventInDb = eventRepository.findByEventId(eventId); //Checking if the event is already available in the DB
				if (eventInDb != null) {
					event.setId(eventInDb.getId()); //Making sure we do not create the new record for same associate
				}
				event.setEventId(cellList.get(0).getStringCellValue());
				event.setMonth(cellList.get(1).getStringCellValue());
				event.setBaseLocation(cellList.get(2).getStringCellValue());
				event.setBenificiaryname(cellList.get(3).getStringCellValue());
				event.setVenue(cellList.get(4).getStringCellValue());
				event.setCouncil(cellList.get(5).getStringCellValue());
				event.setProject(cellList.get(6).getStringCellValue());
				event.setCategory(cellList.get(7).getStringCellValue());
				event.setName(cellList.get(8).getStringCellValue());
				event.setDescription(cellList.get(9).getStringCellValue());
				event.setEventdate(cellList.get(10).getDateCellValue());
				event.setVolunteersCount((long)cellList.get(11).getNumericCellValue());
				event.setVolunteersHours((long)cellList.get(12).getNumericCellValue());
				event.setVolunteersTravelHours((long)cellList.get(13).getNumericCellValue());
				event.setLivesImpacted((long)cellList.get(15).getNumericCellValue());
				event.setActivityType((long)cellList.get(16).getNumericCellValue());
				event.setStatus(cellList.get(17).getStringCellValue());
				String pocIds [] = cellList.get(18).getStringCellValue().split(";");
				String pocNames [] = cellList.get(19).getStringCellValue().split(";");
				String pocContacts [] = cellList.get(20).getStringCellValue().split(";");
				Associate poc = new Associate();
				for (int i = 0; i<pocIds.length; i++) {
					Long associateId = Long.valueOf(pocIds[i]);
					Associate associateInDB = associateRepository.findByAssociateId(associateId);
					if (poc != null) {
						associateInDB.setName(pocNames[i]);
						associateInDB.setContactNumber(pocContacts[i]);
						poc = associateRepository.save(associateInDB);
					}else {
						poc.setAssociateId(associateId);
						poc.setName(pocNames[i]);
						poc.setContactNumber(pocContacts[i]);
						poc = associateRepository.save(poc);
					}
					event.getPoc().add(poc);
				}
				eventRepository.save(event);
			}
		}
	}
	
	
	public void createAssociate(Iterator<Row> rows) {
		boolean isFirstRow = true;
		
		while(rows.hasNext()) {
			if (isFirstRow) {
				isFirstRow = false;
				rows.next();
			}else {
				Associate associate = new Associate();
				Iterator<Cell> cellsIterator = rows.next().iterator();
				List<Cell> cellList = new ArrayList<>();
				cellsIterator.forEachRemaining(cellList::add);
				Long associateId = (long) cellList.get(0).getNumericCellValue();
				Associate associateInDb = associateRepository.findByAssociateId(associateId); //Checking if the associate is already available in the DB
				if (associateInDb != null) {
					associate.setId(associateInDb.getId()); //Making sure we do not create the new record for same associate
				}
				associate.setAssociateId((long)cellList.get(0).getNumericCellValue());
				associate.setName(cellList.get(1).getStringCellValue());
				associate.setDesignation(cellList.get(2).getStringCellValue());
				associate.setLocation(cellList.get(3).getStringCellValue());
				associate.setBusinessUnit(cellList.get(4).getStringCellValue());
				associateRepository.save(associate);
			}
		}
	}

}
