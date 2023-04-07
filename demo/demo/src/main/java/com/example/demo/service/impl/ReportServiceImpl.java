package com.example.demo.service.impl;

import com.example.demo.service.ReportService;
import com.example.demo.web.rest.dto.IncredibleVoiceTemplateDTO;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Service
public class ReportServiceImpl implements ReportService {

    /**collection
    *{
     *     "maKhachHang": "",
     *     "soTK": "",
     *     "tenKhachHang": "Nguyễn Văn A",
     *     "ngheNghiep": "Lập trình viên",
     *     "chucVu": "Dev",
     *     "thuNhap": "100 triệu",
     *     "cbLoiNhuan": "",
     *     "cbTienBDS": "",
     *     "cbChuaXacDinh": "",
     *     "cbQuaTangThuaKe": "",
     *     "cbTienLuong": "",
     *     "cbTietKiem": "",
     *     "cbThuNhap": "",
     *     "tenCoQuan": "BmbSoft",
     *     "diaChiCoQuan": "Trịnh Văn Bô, Xuân Phương, Nam Từ Liêm, Hà Nội",
     *     "sdtCoQuan": "0923573463",
     *     "noiLapPhieu": "Hà Nội",
     *     "ngay": "20",
     *     "thang": "12",
     *     "nam": "2022",
     *     "inputter": "inputter kí",
     *     "nguoiKiemSoat": "kiểm soát kí",
     *     "authorier": "autho kí",
     *     "coCode": "côCde",
     *     "coName": "coName",
     *     "soGDHeader": "5678467834573",
     *     "ngayGioHeader": "20/12/2022",
     *     "user": "userHeader"
     * }
     */
    public byte[] exportIncredibleVoice(IncredibleVoiceTemplateDTO item) throws JRException {
        log.debug("Start ---------------> call api export exportMB14 phieu danh gia tang cuong pdf");
        /**
         * Comment because i don't apply Multi report
         */
//        List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(new ClassPathResource("templates/jasperReport/MB14_PhieuDanhGiaTangCuong.jrxml").getInputStream());
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("logo", new ClassPathResource("image/logoHeader.png").getInputStream());

            parameters.put("coCode", item.getCoCode() != null ?  item.getCoCode() : "");
            parameters.put("coName", item.getCoName() != null ?  item.getCoName() : "");
            parameters.put("soGDHeader", item.getSoGDHeader() != null ?  item.getSoGDHeader() : "");
            parameters.put("ngayGioHeader", item.getNgayGioHeader() != null ?  item.getNgayGioHeader() : "");
            parameters.put("user", item.getUser() != null ?  item.getUser() : "");

            parameters.put("maKhachHang", item.getMaKhachHang());
            parameters.put("soTK", item.getSoTK());
            parameters.put("tenKhachHang",item.getTenKhachHang());
            parameters.put("ngheNghiep", item.getNgheNghiep());
            parameters.put("chucVu", item.getChucVu());
            parameters.put("thuNhap", item.getThuNhap());

            parameters.put("cbLoiNhuan", item.getCbLoiNhuan());
            parameters.put("cbTienBDS", item.getCbTienBDS());
            parameters.put("cbChuaXacDinh", item.getCbChuaXacDinh());
            parameters.put("cbQuaTangThuaKe", item.getCbQuaTangThuaKe());
            parameters.put("cbTienLuong", item.getCbTienLuong());
            parameters.put("cbTietKiem", item.getCbTietKiem());
            parameters.put("cbThuNhap", item.getCbThuNhap());

            parameters.put("tenCoQuan", item.getTenCoQuan());
            parameters.put("diaChiCoQuan", item.getDiaChiCoQuan());
            parameters.put("sdtCoQuan", item.getSdtCoQuan());

            parameters.put("noiLapPhieu", item.getNoiLapPhieu());
            parameters.put("ngay", item.getNgay());
            parameters.put("thang", item.getThang());
            parameters.put("nam", item.getNam());

            parameters.put("inputter", item.getInputter());
            parameters.put("nguoiKiemSoat", item.getNguoiKiemSoat());
            parameters.put("authorier", item.getAuthorier());

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
            return JasperExportManager.exportReportToPdf(jasperPrint);
            /**
             * Multi report
             */
//            jasperPrintList.add(jasperPrint);
//            JRPdfExporter exporter = new JRPdfExporter();
//            ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
//            exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrintList));
//            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(pdfOutputStream));
//
//            SimplePdfReportConfiguration reportConfig = new SimplePdfReportConfiguration();
//            reportConfig.setSizePageToContent(true);
//            reportConfig.setForceLineBreakPolicy(false);
//            exporter.exportReport();
//            return pdfOutputStream.toByteArray();
        } catch (JRException | IOException e) {
            throw new JRException(e.getMessage());
        }
    }

    //postman:
//    {
//        "mailDto": {
//        "emailTo": ["lethesieu@gmail.com"],
//        "subject": "test mail attach",
//                "template": "Công ty Công Nghệ KienNT kính gửi Quý khách hàng Senior Siêu"
//    },
//        "incredibleVoiceTemplateDTO": {
//        "maKhachHang": "1998",
//                "soTK": "1998",
//                "tenKhachHang": "Lê Thế Siêu",
//                "ngheNghiep": "Lập trình viên",
//                "chucVu": "Senior java",
//                "thuNhap": "100 triệu",
//                "cbLoiNhuan": "X",
//                "cbTienBDS": "X",
//                "cbChuaXacDinh": "X",
//                "cbQuaTangThuaKe": "X",
//                "cbTienLuong": "X",
//                "cbTietKiem": "",
//                "cbThuNhap": "X",
//                "tenCoQuan": "BmbSoft",
//                "diaChiCoQuan": "Trịnh Văn Bô, Xuân Phương, Nam Từ Liêm, Hà Nội",
//                "sdtCoQuan": "0923573463",
//                "noiLapPhieu": "Hà Nội",
//                "ngay": "07",
//                "thang": "04",
//                "nam": "2023",
//                "inputter": "Nguyễn Trung Kiên",
//                "nguoiKiemSoat": "Kiên",
//                "authorier": "Kien",
//                "coCode": "VND",
//                "coName": "TP BAnk",
//                "soGDHeader": "5678467834573",
//                "ngayGioHeader": "07/04/2023",
//                "user": "Kien"
//    }
//    }

}
