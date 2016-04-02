package paohaijiao.storm.realtime.clickTopology;

import java.util.Map;
import java.util.Random;

import ch.qos.logback.classic.pattern.Util;
import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import backtype.storm.utils.Utils;

public class HelloWorldSpout extends BaseRichSpout {
	private int referenceRandom;
	private SpoutOutputCollector collector;
	private static final int MAX_RANDOM = 10;

	public HelloWorldSpout() {
		final Random random = new Random();
		referenceRandom = random.nextInt(MAX_RANDOM);
	}

	public void open(Map conf, TopologyContext context,
			SpoutOutputCollector collector) {
		this.collector = collector;

	}

	public void nextTuple() {
		Utils.sleep(100);
		int instanceRandom=new Random().nextInt(MAX_RANDOM);
		if(instanceRandom==referenceRandom){
			collector.emit(new Values("Hello World"));
		}else{
			collector.emit(new Values("Other random world"));
		}

	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		
		declarer.declare(new Fields("sentence"));
	}

}
