package com.metamagic.desire;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/hello")
public class HelloController {

	@Autowired
	private GreetingRepository greetingRepository;
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@RequestMapping("/")
	public @ResponseBody List<Greeting> findall(){
		List<Greeting> result = greetingRepository.findAll();
		return result;
	}
	
	@RequestMapping("/save")
	public @ResponseBody String save(){
		log.info("RECORD SAVED");
		Set<TestImpl> tests= new HashSet<TestImpl>();
		tests.add(new TestImpl("1","1"));
		Greeting greeting = new Greeting("liu", "hi",tests);
		greetingRepository.save(greeting);
		List<Greeting> result = greetingRepository.findAll();
		return "RECORD SAVED";
	}

	@RequestMapping("/update")
	public @ResponseBody String update(){
		log.info("RECORD UPDATED");
		Greeting greeting = greetingRepository.findById("16285d82-b29a-4b37-919d-b7ea6ee180f8");
		greeting.setMsg(greeting.getMsg() +"-changed");
		for (Iterator iterator = greeting.getTests().iterator(); iterator.hasNext();) {
			TestImpl type = (TestImpl) iterator.next();
			type.setName(type.getName()+"--CHANGED");
		}
		greetingRepository.update(greeting);
		return "RECORD UPDATED";
	}

	@RequestMapping("/updateall")
	public @ResponseBody String updateall(){
		log.info("ALL RECORD UPDATED");
		List<Greeting> greetings = greetingRepository.findAll();
		
		for (Iterator iterator = greetings.iterator(); iterator.hasNext();) {
			Greeting greeting = (Greeting) iterator.next();
			
			greeting.setMsg("-ABCD-"+greeting.getMsg() );
			for (Iterator iterator1 = greeting.getTests().iterator(); iterator1.hasNext();) {
				TestImpl type = (TestImpl) iterator1.next();
				type.setName("ABCD--"+type.getName());
			}
			greetingRepository.update(greeting);
		}
		return "ALL RECORD UPDATED";
	}

	@RequestMapping("/removechilds")
	public @ResponseBody String removechilds(){
		log.info("REMOVED ALL CHILDS");
		List<Greeting> greetings = greetingRepository.findAll();
		
		for (Iterator iterator = greetings.iterator(); iterator.hasNext();) {
			Greeting greeting = (Greeting) iterator.next();
			greeting.setMsg("-ABCD-"+greeting.getMsg() );
			for (Iterator iterator1 = greeting.getTests().iterator(); iterator1.hasNext();) {
				TestImpl type = (TestImpl) iterator1.next();
				iterator1.remove();
			}
			greetingRepository.update(greeting);
		}
		return "REMOVED ALL CHILDS";
	}
	
}
