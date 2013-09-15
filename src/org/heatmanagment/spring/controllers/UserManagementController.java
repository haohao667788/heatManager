package org.heatmanagment.spring.controllers;

import java.util.List;

import org.heatmanagment.hibernate.domain.MachinesetInfo;
import org.heatmanagment.hibernate.domain.UsersInfo;
import org.heatmanagment.hibernate.util.DateConverter;
import org.heatmanagment.hibernate.util.FileUploader;
import org.heatmanagment.hibernate.util.MapperTool;
import org.heatmanagment.spring.entity.SuccessOut;
import org.heatmanagment.spring.services.MachineSetService;
import org.heatmanagment.spring.services.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Controller
@RequestMapping("/data/fare")
public class UserManagementController {

	@Autowired
	private UserManagementService userManagementService;

	@Autowired
	private MachineSetService machineSetService;

	@Autowired
	private FileUploader fileUploader;

	@Autowired
	private MapperTool tool;

	@RequestMapping("/user/update")
	@ResponseBody
	public String saveOrUpdate(
			@RequestParam(required = false) Long usrid,
			@RequestParam String usrname,
			@RequestParam String usrtype,
			@RequestParam String address,
			@RequestParam String phone,
			@RequestParam String startdate,
			@RequestParam String contractdate,
			@RequestParam String contracttype,
			@RequestParam String contractver,
			@RequestParam(required = false, value = "contractpic") CommonsMultipartFile contractpicFile,
			@RequestParam(required = false, value = "idpic") CommonsMultipartFile idpicFile,
			@RequestParam(required = false, value = "houseidpic") CommonsMultipartFile houseidpicFile,
			@RequestParam(required = false, value = "housepic") CommonsMultipartFile housepicFile,
			@RequestParam(required = false) String desp,
			@RequestParam Long pjtid, @RequestParam Long mchid,
			@RequestParam Long cmtid, @RequestParam Long bldid,
			@RequestParam Long untid,
			@RequestParam(required = false) Double area,
			@RequestParam(required = false) Double realarea,
			@RequestParam(required = false) Double feearea,
			@RequestParam(required = false) String feetype,
			@RequestParam(required = false) Double feerate,
			@RequestParam(required = false) Double discount,
			@RequestParam(required = false) Double reducefee,
			@RequestParam(required = false) String heatstate,
			@RequestParam(required = false) Double heatbase,
			@RequestParam(required = false) Double heatrate,
			@RequestParam(required = false) String housetype) {

		SuccessOut out = new SuccessOut();
		out.reset();
		/**
		 * deal with the pics
		 */
		String ctrtPicfilePath = null;
		String idPicfilePath = null;
		String hsIdPicfilePath = null;
		String hsPicfilePath = null;
		String tempPath;

		UsersInfo temp = this.userManagementService.findById(usrid);

		if (contractpicFile != null & !contractpicFile.isEmpty()) {
			// upload the file
			if (usrid != null) {
				// user want to upload a new version of pic
				tempPath = temp.getContractpic();
				if (tempPath != null && !tempPath.equals("")) {
					this.fileUploader.deleteFile(tempPath);
				}
			}
			ctrtPicfilePath = this.fileUploader.upload(contractpicFile, "user");
		}
		if (idpicFile != null & !idpicFile.isEmpty()) {
			// upload the file
			if (usrid != null) {
				// user want to upload a new version of pic
				tempPath = temp.getIdpic();
				if (tempPath != null && !tempPath.equals("")) {
					this.fileUploader.deleteFile(tempPath);
				}
			}
			idPicfilePath = this.fileUploader.upload(idpicFile, "user");
		}
		if (houseidpicFile != null & !houseidpicFile.isEmpty()) {
			// upload the file
			if (usrid != null) {
				// user want to upload a new version of pic
				tempPath = temp.getHouseidpic();
				if (tempPath != null && !tempPath.equals("")) {
					this.fileUploader.deleteFile(tempPath);
				}
			}
			hsIdPicfilePath = this.fileUploader.upload(houseidpicFile, "user");
		}
		if (housepicFile != null & !housepicFile.isEmpty()) {
			// upload the file
			if (usrid != null) {
				// user want to upload a new version of pic
				tempPath = temp.getHousepic();
				if (tempPath != null && !tempPath.equals("")) {
					this.fileUploader.deleteFile(tempPath);
				}
			}
			hsPicfilePath = this.fileUploader.upload(housepicFile, "user");
		}
		this.userManagementService.saveOrUpdate(usrid, usrname, usrtype,
				address, phone, DateConverter.convert(startdate),
				DateConverter.convert(contractdate), contracttype, contractver,
				ctrtPicfilePath, idPicfilePath, hsIdPicfilePath, hsPicfilePath,
				cmtid, bldid, untid, mchid, pjtid, area, realarea, feearea,
				feetype, feerate, discount, reducefee, heatstate, heatbase,
				heatrate, housetype, desp);
		return this.tool.successRetort();
	}

	@RequestMapping("/user/del")
	@ResponseBody
	public String delete(@RequestParam Long usrid) {
		this.userManagementService.delete(usrid);
		return this.tool.successRetort();
	}

	@RequestMapping("/user/list")
	@ResponseBody
	public String listItems(
			@RequestParam(required = false, defaultValue = "0") Integer start,
			@RequestParam(required = false, defaultValue = "20") Integer limit) {
		SuccessOut out = new SuccessOut();
		out.reset();
		if (start == null) {
			start = new Integer(0);
		}
		if (limit == null) {
			limit = new Integer(20);
		}
		List users = this.userManagementService.listItems(start, limit);
		out.setData(users);
		out.setTotalProperty(this.userManagementService.count());
		return this.tool.result(out);
	}

	@RequestMapping("/user/relateMch")
	@ResponseBody
	public String relateMachine(@RequestParam Long bldid) {
		List<Object> temp = this.machineSetService.findMchByBld(bldid);
		SuccessOut out = new SuccessOut();
		out.reset();
		out.setData(temp);
		return this.tool.result(out);
	}

	@RequestMapping("/user/queryPjt")
	@ResponseBody
	public String queryPjt() {
		return "[[4,\"43125\"]]";
	}
}
