package ua.elips.objects;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;
import ua.elips.controller.Controller;
import ua.elips.interfaces.impls.CollectionGapTable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static ua.elips.objects.Calculate.vb;
import static ua.elips.objects.Calculate.vd;

public class SaveToWorld {
    public String headerContent, footerContent, fileContent;



    private Calculate calc = new Calculate();
    private CollectionGapTable gapTableImpl = new CollectionGapTable();

    public void toWord() throws IOException {


        headerContent = "ДЕРЖАВНИЙ НАУКОВО-ВИПРОБУВАЛЬНИЙ ІНСТИТУТ\n" +
                "ВИПРОБУВАНЬ І СЕРТИФІКАЦІЇ ОЗБРОЄННЯ ТА ВІЙСЬКОВОЇ ТЕХНІКИ ЗБРОЙНИХ СИЛ УКРАЇНИ";
        footerContent = " 14033 м. Чернігів ";

        fileContent = "Програма розрахунку ";

        // создаем модель docx документа, к которой будем прикручивать наполнение (колонтитулы, текст)
        XWPFDocument docxModel = new XWPFDocument();
        CTSectPr ctSectPr = docxModel.getDocument().getBody().addNewSectPr();

        // получаем экземпляр XWPFHeaderFooterPolicy для работы с колонтитулами
        XWPFHeaderFooterPolicy headerFooterPolicy = new XWPFHeaderFooterPolicy(docxModel, ctSectPr);

        // создаем верхний колонтитул Word файла
        CTP ctpHeaderModel = createHeaderModel("");


        // устанавливаем сформированный верхний
        // колонтитул в модель документа Word
        XWPFParagraph headerParagraph = new XWPFParagraph(ctpHeaderModel, docxModel);
        headerParagraph.setAlignment(ParagraphAlignment.CENTER);
        headerParagraph.setBorderBottom(Borders.BASIC_BLACK_SQUARES);



        XWPFRun hederparagraphConfig = headerParagraph.createRun();
        hederparagraphConfig.setText(headerContent);
        //hederparagraphConfig.addBreak();
        hederparagraphConfig.setFontSize(12);
        hederparagraphConfig.setFontFamily("Time New Roman");
        hederparagraphConfig.setColor("0635aa");

        headerFooterPolicy.createHeader(
                XWPFHeaderFooterPolicy.DEFAULT,
                new XWPFParagraph[]{headerParagraph}
        );

        // создаем нижний колонтитул docx файла
        CTP ctpFooterModel = createFooterModel("");

        // устанавливаем сформированый нижний колонтитул в модель документа Word
        XWPFParagraph footerParagraph = new XWPFParagraph(ctpFooterModel, docxModel);
        footerParagraph.setAlignment(ParagraphAlignment.CENTER);
        footerParagraph.setBorderTop(Borders.BASIC_BLACK_SQUARES);

        XWPFRun fotterparagraphConfig = footerParagraph.createRun();
        fotterparagraphConfig.addBreak();
        fotterparagraphConfig.setText(footerContent);
        fotterparagraphConfig.setFontFamily("Time New Roman");
        fotterparagraphConfig.setColor("0635aa");

        headerFooterPolicy.createFooter(
                XWPFHeaderFooterPolicy.DEFAULT,
                new XWPFParagraph[]{footerParagraph}
        );

        // создаем обычный параграф

        XWPFParagraph bodyParagraph = docxModel.createParagraph();
        bodyParagraph.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun document = bodyParagraph.createRun();
        document.setFontSize(12);
        document.setFontFamily("Time New Roman");

        document.setText(fileContent);

        document.addBreak();
        document.addBreak();
        document.setText("Координати вогневої позиції:  " + "  X = " + calc.xVp + ",  Y = " + calc.yVp );
        document.addBreak();
        document.setText("Кількість пострілів -  " + calc.count );
        document.addBreak();
        document.addBreak();
        document.setText("Координати розривів:");
        document.addBreak();

        int i = 0;
        for (Gap gap : gapTableImpl.getGapList()) {
            i++;
            document.setText(i + ") X = " + gap.getX() + "; Y = " + gap.getY() + "; Д вп-р = " + gap.getD() + "; А вп-р = " + gap.getA());
        }
        document.addBreak();
        document.setText("Центр групи розривів: " );
        document.addBreak();
        document.setText("Координати: X = " + calc.calculateXcgr() + ", Y = " + calc.calculateYcgr());
        document.addBreak();
        document.setText("Середня лінія стрільби:  ");
        document.setText("Д =  " + calc.calculateDcgr() + ",   Кут = " + calc.calculateAcgr());
        document.addBreak();
        document.addBreak();
        document.setText("Відхилення:  Вд = " + vd + "  Вб = " + vb);


        // сохраняем модель docx документа в файл
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Еліпс. Збереження файлу");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Документ Word ", "*.docx");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialFileName("11111");
        File userDirectory = new File("D:/");
        fileChooser.setInitialDirectory(userDirectory);

        File file = fileChooser.showSaveDialog((new Stage()));


        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(new File(file.getAbsolutePath()));

        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        try {
            docxModel.write(outputStream);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            outputStream.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }

    private static CTP createHeaderModel(String headerContent) {
        // создаем хедер или верхний колонтитул
        CTP ctpHeaderModel = CTP.Factory.newInstance();
        CTR ctrHeaderModel = ctpHeaderModel.addNewR();
        CTText cttHeader = ctrHeaderModel.addNewT();

        cttHeader.setStringValue(headerContent);
        return ctpHeaderModel;
    }

    private static CTP createFooterModel(String footerContent) {
        // создаем футер или нижний колонтитул
        CTP ctpFooterModel = CTP.Factory.newInstance();
        CTR ctrFooterModel = ctpFooterModel.addNewR();
        CTText cttFooter = ctrFooterModel.addNewT();

        cttFooter.setStringValue(footerContent);
        return ctpFooterModel;
    }



}

