package com.ctrip.hermes.meta.resource;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.codehaus.plexus.util.StringUtils;

import com.ctrip.hermes.core.utils.PlexusComponentLocator;
import com.ctrip.hermes.meta.entity.Topic;
import com.ctrip.hermes.meta.pojo.TopicView;
import com.ctrip.hermes.meta.server.RestException;
import com.ctrip.hermes.meta.service.TopicService;

@Path("/topics/")
@Singleton
@Produces(MediaType.APPLICATION_JSON)
public class TopicResource {

	private static TopicService topicService = PlexusComponentLocator.lookup(TopicService.class);

	@POST
	@Path("")
	public Response createTopic(String content) {
		if (StringUtils.isEmpty(content)) {
			throw new RestException("HTTP POST body is empty", Status.BAD_REQUEST);
		}
		return Response.status(Status.CREATED).entity(content).build();
	}

	@GET
	@Path("{name}")
	public TopicView getTopic(@PathParam("name") String name) {
		Topic topic = topicService.getTopic(name);
		if (topic == null) {
			throw new RestException("Topic not found: " + name, Status.NOT_FOUND);
		}
		return new TopicView(topic);
	}

	@GET
	@Path("")
	public List<TopicView> findTopics(@QueryParam("pattern") String pattern) {
		if(StringUtils.isEmpty(pattern)){
			pattern = ".*";
		}
		
		List<Topic> topics = topicService.findTopics(pattern);
		List<TopicView> returnResult = new ArrayList<TopicView>();
		for (Topic topic : topics) {
			returnResult.add(new TopicView(topic));
		}
		return returnResult;
	}

}