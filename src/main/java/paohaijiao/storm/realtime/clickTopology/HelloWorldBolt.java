package paohaijiao.storm.realtime.clickTopology;

import java.util.Map;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;

public class HelloWorldBolt extends BaseRichBolt {
    private static int mycount=0;
	public void prepare(Map stormConf, TopologyContext context,
			OutputCollector collector) {
	}

	public void execute(Tuple input) {
		String test=input.getStringByField("sentence");
		if("Hello World".equals(test)){
			mycount++;
			System.out.println("Found a Hello World ! My Count is now :"+Integer.toString(mycount));
			
		}

	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// TODO Auto-generated method stub

	}

}
