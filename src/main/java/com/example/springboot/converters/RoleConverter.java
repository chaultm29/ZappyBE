package com.example.springboot.converters;

import java.util.ArrayList;
import java.util.List;

import com.example.springboot.dto.RoleDTO;
import com.example.springboot.entities.RoleEntity;

public class RoleConverter {
	public RoleEntity toEntity(RoleDTO roleDTO) {
		RoleEntity roleEntity = new RoleEntity();
		roleEntity.setId(roleDTO.getId());
		roleEntity.setName(roleDTO.getName());
		return roleEntity;
	}

	public RoleDTO toDTO(RoleEntity roleEntity) {
		RoleDTO roleDTO = new RoleDTO();
		roleDTO.setId(roleEntity.getId());
		roleDTO.setName(roleEntity.getName());
		return roleDTO;
	}

	public List<RoleDTO> toDTOs(List<RoleEntity> listEntities) {
		ArrayList<RoleDTO> roleDTOs = new ArrayList<RoleDTO>();
		for (RoleEntity roleEntity : listEntities) {
			roleDTOs.add(toDTO(roleEntity));
		}
		return roleDTOs;
	}
}
