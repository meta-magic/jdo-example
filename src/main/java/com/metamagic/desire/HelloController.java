package com.metamagic.desire;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.jdo.JDOHelper;

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
		Message message = new Message("hello");
		tests.add(new TestImpl("1","1",message));
		InfoClass infoClass = new InfoClass("info");
		Greeting greeting = new Greeting("liu", "hi",tests, infoClass);
		greetingRepository.save(greeting);
		List<Greeting> result = greetingRepository.findAll();
		return "RECORD SAVED";
	}

	@RequestMapping("/update")
	public @ResponseBody String update(){
		log.info("RECORD UPDATED");
		Greeting greeting = greetingRepository.findById("c968e1fe-f32f-48fd-bb89-70cf2e2c9039");
		greeting.setMsg(greeting.getMsg() +"-changed");
		/* UPDATE SECOND EMBEDDED OBJECT  */
		InfoClass infoClass = greeting.getInfoClass();
		infoClass.setInfoId(infoClass.getInfoId() + "--CHANGED");
		greeting.setInfoClass(infoClass);
		System.out.println(" states "+ JDOHelper.getObjectState(greeting.getTests()));
		for (Iterator<TestImpl> iterator = greeting.getTests().iterator(); iterator.hasNext();) {
			TestImpl type = (TestImpl) iterator.next();
			System.out.println(" states  dsdfdfdsfs "+ JDOHelper.getObjectState(greeting.getTests()));
			System.out.println(" state before "+ JDOHelper.getObjectState(type));
			type.setName(type.getName()+"--CHANGED");
			/* update second level embedded object  */
			type.getMessage().setData(type.getMessage().getData() + "--UPDATED");
			System.out.println(" state after "+ JDOHelper.getObjectState(type));
			System.out.println(" states "+ JDOHelper.getObjectState(greeting.getTests()));
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
			InfoClass infoClass = greeting.getInfoClass();
			infoClass.setInfoId(infoClass.getInfoId() + "--CHANGED");
			greeting.setInfoClass(infoClass);
			for (Iterator iterator1 = greeting.getTests().iterator(); iterator1.hasNext();) {
				TestImpl type = (TestImpl) iterator1.next();
				type.setName("ABCD--"+type.getName());
				type.getMessage().setData(type.getMessage().getData() + "--UPDATED");
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
