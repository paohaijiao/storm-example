package paohaijiao.storm.realtime.clickTopology;

import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.Config;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.utils.Utils;
//compile exec:java -Dexec.classpathScope=compile -Dexec.mainClass=paohaijiao.storm.realtime.helloworld.HelloWorldTopology
public class HelloWorldTopology {
	public static void main(String[] args) throws Exception, InvalidTopologyException {
		TopologyBuilder builder=new TopologyBuilder();
		builder.setSpout("randomHelloWorld", new HelloWorldSpout(),10 );
		builder.setBolt("HelloWorldBolt", new HelloWorldBolt(),2).shuffleGrouping("randomHelloWorld");
		Config config=new Config();
		if(args!=null&&args.length>0){
			config.setNumWorkers(3);
			StormSubmitter.submitTopology(args[0], config, builder.createTopology());
			builder.createTopology();
		}
		LocalCluster cluster=new LocalCluster();
		cluster.submitTopology("test", config, builder.createTopology());
		Utils.sleep(1000);
		cluster.killTopology("test");
		cluster.shutdown();
		
		
		

	}

}
