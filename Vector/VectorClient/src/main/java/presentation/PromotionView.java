package presentation;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import presentation.common.SingletonItem;
import vo.ActivityPromotionVo;

import java.net.URL;
import java.time.ZoneId;
import java.util.ResourceBundle;

/**
 * @author Molloh
 * @version 2016/12/25
 * @description
 */
public class PromotionView implements Initializable {
    @FXML
    private TitledPane promotion_pane;
    @FXML
    private TextField proName_field;
    @FXML
    private TextField proDiscount_field;
    @FXML
    private DatePicker proStart_date;
    @FXML
    private DatePicker proEnd_date;
    @FXML
    private ComboBox<String> proType_choice;

    private ActivityPromotionVo promotionVo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        promotionVo = SingletonItem.getInstance().getActivityPromotionVo();

        promotion_pane.setGraphic(new Button(""));
        proName_field.setText(promotionVo.getPromotionName());
        proType_choice.setValue(promotionVo.getPromotionType().name());
        proStart_date.setValue(promotionVo.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        proEnd_date.setValue(promotionVo.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        proDiscount_field.setText(String.valueOf(promotionVo.getDiscount()));

        proName_field.setEditable(false);
        proDiscount_field.setEditable(false);
        proType_choice.setEditable(false);
        proStart_date.setEditable(false);
        proEnd_date.setEditable(false);
    }

    @FXML
    private void handleEdit() {
        proName_field.setEditable(true);
        proDiscount_field.setEditable(true);
        proType_choice.setEditable(true);
        proStart_date.setEditable(true);
        proEnd_date.setEditable(true);
    }
}
