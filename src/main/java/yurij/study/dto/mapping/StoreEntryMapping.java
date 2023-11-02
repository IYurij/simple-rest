package yurij.study.dto.mapping;

import org.springframework.stereotype.Component;
import yurij.study.dto.StoreEntryDTO;
import yurij.study.entity.StoreEntryEntity;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

/**
 * Mapping component for StoreEntry objects.
 */
@Component
public class StoreEntryMapping {
    /**
     * Convert from entity to dto.
     *
     * @param entity StoreEntryEntity object
     * @return StoreEntryDTO object
     */
    public StoreEntryDTO toDTO(@Nonnull StoreEntryEntity entity) {
        return new StoreEntryDTO(
                entity.getKey(),
                entity.getValue()
        );

    }

    /**
     * Convert from dto to entity.
     *
     * @param dto StoreEntryDTO object
     * @return StoreEntryEntity object
     */
    public StoreEntryEntity toEntity(StoreEntryDTO dto) {
        return new StoreEntryEntity(
                dto.getKey(),
                dto.getValue()
        );
    }

    /**
     * Convert from entitiesList to DTOList
     *
     * @param entityArrayList entities list
     * @return dto's list
     */
    public List<StoreEntryDTO> toDTOList(ArrayList<StoreEntryEntity> entityArrayList) {

        return entityArrayList.stream()
                .map(this::toDTO)
                .toList();
    }
}
