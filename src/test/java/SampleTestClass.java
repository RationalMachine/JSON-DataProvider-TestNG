import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class SampleTestClass {

  @Test(dataProvider = "getData")
  public void testMethod(TestData data) {
    //System.out.println(data);
    System.out.println(data.getUsername()+" "+data.getPassword());
  }

  @DataProvider
  public Object[][] getData() throws FileNotFoundException {
    File file=new File("src/main/resources/data.json");
    JsonElement jsonData = new JsonParser().parse(new FileReader(file.getAbsolutePath()));
    JsonElement dataSet = jsonData.getAsJsonObject().get("dataSet");
    List<TestData> testData = new Gson().fromJson(dataSet, new TypeToken<List<TestData>>() {
    }.getType());
    Object[][] returnValue = new Object[testData.size()][1];
    int index = 0;
    for (Object[] each : returnValue) {
      each[0] = testData.get(index++);
    }
    return returnValue;
  }

}
