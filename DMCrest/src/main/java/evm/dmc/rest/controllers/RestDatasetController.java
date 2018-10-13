package evm.dmc.rest.controllers;

import evm.dmc.webApi.dto.MetaDataDto;

import java.util.List;

public class RestDatasetController extends AbstractRestCrudController<MetaDataDto> {

    @Override
    public MetaDataDto addInstance(Long accountId, MetaDataDto dto) {
        return null;
    }

    @Override
    public MetaDataDto updateInstance(Long accountId, MetaDataDto dto) {
        return null;
    }

    @Override
    public MetaDataDto deleteInstance(Long accountId, Long id) {
        return null;
    }

    @Override
    public MetaDataDto deleteInstance(Long accountId, MetaDataDto dto) {
        return null;
    }

    @Override
    public MetaDataDto getInstance(Long accountId, Long id) {
        return null;
    }

    @Override
    public List<MetaDataDto> getInstancesList(Long accountId) {
        return null;
    }
}
