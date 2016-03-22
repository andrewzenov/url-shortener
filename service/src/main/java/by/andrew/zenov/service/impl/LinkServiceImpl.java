package by.andrew.zenov.service.impl;

import by.andrew.zenov.data.dao.LinkDao;
import by.andrew.zenov.data.model.Link;
import by.andrew.zenov.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Andrew on 10.03.2016.
 */
@Service(value = "linkService")
public class LinkServiceImpl implements LinkService {

	@Autowired
	private LinkDao linkDao;

	@Override
	public Link get(String shortUrl) {
		return linkDao.get(shortUrl);
	}

	@Override
	public List<Link> getAll() {
		return linkDao.getAll();
	}

	@Override
	@Transactional
	public void delete(String shortUrl) {
		linkDao.delete(shortUrl);
	}

	@Override
	@Transactional
	public void delete(Link link) {
		linkDao.delete(link.getShortUrl());
	}

	@Override
	@Transactional
	public void insert(Link link) {
		linkDao.insert(link);
	}

	@Override
	@Transactional
	public void update(Link link) {
		linkDao.update(link);
	}
}
