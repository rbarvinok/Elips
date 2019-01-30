package ua.elips.objects;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;
import ua.elips.interfaces.impls.CollectionGapTable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static ua.elips.controller.Controller.calc;
import static ua.elips.controller.Controller.gapTableImpl;
import static ua.elips.objects.Calculate.vb;
import static ua.elips.objects.Calculate.vd;

public class SaveToWorld {
    public String headerContent, footerContent, fileContent;


    public void toWord() throws IOException {


        headerContent = "ДЕРЖАВНИЙ НАУКОВО-ВИПРОБУВАЛЬНИЙ ІНСТИТУТ ВИПРОБУВАНЬ І СЕРТИФІКАЦІЇ ОЗБРОЄННЯ ТА ВІЙСЬКОВОЇ ТЕХНІКИ ";
        footerContent = " 14033 м. Чернігів ";

        fileContent = "Розрахунок розсіювання точок падіння (розривів) за результатами стрільб артилерії ";

        // создаем модель docx документа, к которой будем прикручивать наполнение (колонтитулы, текст)
        XWPFDocument docxModel = new XWPFDocument();
        CTSectPr ctSectPr = docxModel.getDocument().getBody().addNewSectPr();

        // получаем экземпляр XWPFHeaderFooterPolicy для работы с колонтитулами
        XWPFHeaderFooterPolicy headerFooterPolicy = new XWPFHeaderFooterPolicy(docxModel, ctSectPr);

        // создаем верхний колонтитул Word файла
        CTP ctpHeaderModel = createHeaderModel("");


        // устанавливаем сформированный верхнийколонтитул в модель документа Word
        XWPFParagraph headerParagraph = new XWPFParagraph(ctpHeaderModel, docxModel);
        headerParagraph.setAlignment(ParagraphAlignment.CENTER);
        headerParagraph.setBorderBottom(Borders.BASIC_BLACK_SQUARES);


        XWPFRun hederparagraphConfig = headerParagraph.createRun();
        hederparagraphConfig.setText(headerContent);
        hederparagraphConfig.setFontSize(12);
        hederparagraphConfig.setBold(false);
        hederparagraphConfig.setFontFamily("Time New Roman");
        hederparagraphConfig.setColor("0000ff");

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
        fotterparagraphConfig.setColor("0000ff");

        headerFooterPolicy.createFooter(
                XWPFHeaderFooterPolicy.DEFAULT,
                new XWPFParagraph[]{footerParagraph}
        );

        // создаем обычный параграф
        XWPFParagraph bodyParagraph = docxModel.createParagraph();
        bodyParagraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun document = bodyParagraph.createRun();
        document.setFontSize(14);
        document.setFontFamily("Time New Roman");
        document.setBold(true);
        document.setTextPosition(25);
        document.setText(fileContent);
        document.addBreak();

        XWPFParagraph bodyParagraph1 = docxModel.createParagraph();
        XWPFRun document1 = bodyParagraph1.createRun();
        bodyParagraph1.setAlignment(ParagraphAlignment.LEFT);
        document1.setFontSize(12);
        document1.setFontFamily("Time New Roman");
        document1.setText("Координати вогневої позиції:  ");
        document1.addBreak();
        document1.setText("X = " + calc.xVp + ",  Y = " + calc.yVp);
        document1.addBreak();
        document1.setText("Кількість пострілів -  " + calc.count);
        document1.addBreak();
        document1.addBreak();
        document1.setText("Координати розривів:");
        document1.addBreak();

        int i = 0;
        for (Gap gap : gapTableImpl.getGapList()) {
            i++;
            document1.setText(i + ") X = " + gap.getX() + "; Y = " + gap.getY() + "; Д вп-р = " + gap.getD() + "; А вп-р = " + gap.getA());
            document1.addBreak();
            System.out.println(i + ") X = " + gap.getX() + "; Y = " + gap.getY() + "; Д вп-р = " + gap.getD() + "; А вп-р = " + gap.getA());
        }

        document1.addBreak();
        document1.setText("Центр групи розривів: ");
        document1.addBreak();
        document1.setText("Координати: ");
        document1.addBreak();
        document1.setText("X = " + calc.calculateXcgr() + ",  Y = " + calc.calculateYcgr());
        document1.addBreak();
        document1.setText("Середня лінія стрільби:  ");
        document1.addBreak();
        document1.setText("Д =  " + calc.calculateDcgr() + ",   Кут = " + calc.calculateAcgr());
        document1.addBreak();
        document1.addBreak();
        document1.setText("Відхилення:  ");
        document1.addBreak();
        document1.setText("Вд = " + vd + ",   Вб = " + vb);


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

