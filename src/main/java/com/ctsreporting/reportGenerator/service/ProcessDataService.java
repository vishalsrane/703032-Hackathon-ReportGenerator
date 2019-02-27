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
import com.ctsreporting.reportGenerator.repository.AssociateRepository;

@Service
public class ProcessDataService {
	
	private static String associateDetailsFileName = "AssociateDetails.xls";
	private static String eventSummaryFileName = "Outreach Events Summary.xls";
	private static String eventImformationFileName = "Outreach Event Imformation";
	
	@Autowired
	private AssociateRepository associateRepository;
	
	public void processUpdatedExcels() {
	    saveFile(associateDetailsFileName);
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
