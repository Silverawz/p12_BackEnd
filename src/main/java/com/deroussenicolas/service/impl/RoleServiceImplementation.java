package com.deroussenicolas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deroussenicolas.dao.RoleRepository;
import com.deroussenicolas.entities.Role;
import com.deroussenicolas.service.RoleService;

/**
 * implements RoleService
 * 
 * @author deroussen nicolas
 * 
 */
@Service("RoleServiceImplementation")
@Transactional
public class RoleServiceImplementation implements RoleService {
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role findSpecificRole(String description) {
		return roleRepository.findSpecificRole(description);
	}
}
