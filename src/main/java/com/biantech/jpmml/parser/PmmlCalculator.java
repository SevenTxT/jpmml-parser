package com.biantech.jpmml.parser;
import org.dmg.pmml.FieldName;
import org.jpmml.evaluator.ModelEvaluator;

import java.util.HashMap;
import java.util.Map;

public class PmmlCalculator {
	/**
	 * @param pmmlModelName pmml模型的名字
	 * @param pmmlFilePath pmmlmodel的绝对路径
	 * @param dataParameters 传入的数据map集
	 * @return 
	 */
	 public static double pmmlEvaluator(String pmmlModelName, String pmmlFilePath,HashMap<String, String> dataParameters){
		 double score = 0d;
		 try {
			 PmmlInvoker invoker = new PmmlInvoker();
			 // 第一步 创建评估模型
			 ModelEvaluator evaluator = invoker.getEvaluator(pmmlModelName, pmmlFilePath);
			 // 第二部 对传入的map参数进行 格式转换
			 HashMap<FieldName, Object> dataParameters1 = invoker.dataType(dataParameters);
			 Map<FieldName, ?> result = evaluator.evaluate(dataParameters1); //得到结果集
			 String scoreStr = invoker.scoreResult(result);//对结果集进行选择返回想要的结果
			 if (scoreStr != null) {
				 score = Double.parseDouble(scoreStr);
			 }
		 }catch(Exception ex){
		 	ex.printStackTrace();
		 }
	    return score;
	 }
}
