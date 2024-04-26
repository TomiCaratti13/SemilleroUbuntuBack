package com.semillero.ubuntu.services.impl;

import com.semillero.ubuntu.dtos.CalculoDto;
import com.semillero.ubuntu.dtos.InversionDTO;
import com.semillero.ubuntu.entities.Inversion;
import com.semillero.ubuntu.entities.MicroEmprendimiento;
import com.semillero.ubuntu.entities.Riesgo;
import com.semillero.ubuntu.entities.Usuario;
import com.semillero.ubuntu.repositories.InversionRepository;
import com.semillero.ubuntu.repositories.MicroEmprendimientoRepository;
import com.semillero.ubuntu.repositories.RiesgoRepository;
import com.semillero.ubuntu.repositories.UsuarioRepositorio;
import com.semillero.ubuntu.services.InversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class InversionServiceImp implements InversionService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private MicroEmprendimientoRepository microEmprendimientoRepository;

    @Autowired
    private RiesgoRepository riesgoRepository;
    @Autowired
    private InversionRepository inversionRepository;

    @Override
    @Transactional
    public Inversion crearInversion(CalculoDto calculoDto, Long idRiesgo, Long idUser, Long idMicro) {
        Optional<Usuario> oU = usuarioRepositorio.findById(idUser);
        Optional<MicroEmprendimiento> oM = microEmprendimientoRepository.findById(idMicro);
        Optional<Riesgo> oR = riesgoRepository.findById(idRiesgo);
        Inversion inversion = new Inversion();
        if (oU.isPresent() && oM.isPresent()) {
            inversion.setMonto(calculoDto.getTotal() - calculoDto.getCosto());
            inversion.setCuotas(calculoDto.getCuotas());
            inversion.setRetorno(calculoDto.getRetorno());
            inversion.setGanancias(calculoDto.getGanancias());
            inversion.setMonto_mensual(calculoDto.getRetorno() / calculoDto.getCuotas());
            inversion.setCuotas_faltantes(calculoDto.getCuotas());
            inversion.setFecha_creacion(LocalDate.from(LocalDateTime.now()));
            inversion.setRiesgo(oR.get());
            inversion.setUsuario(oU.get());
            inversion.setMicroEmprendimiento(oM.get());
            inversion.setEstado(true);
        }

        return inversionRepository.save(inversion);
    }



        @Override
        public List<InversionDTO> obtenerMisInversiones(Long id){
            List<Inversion> listainversion = inversionRepository.obtenerMisInversiones(id);
            if (!listainversion.isEmpty()){

                return crearDto(listainversion);

            } else {
                return new ArrayList<>();
            }
        }


        public List<InversionDTO> crearDto(List<Inversion> inversion){
            List<InversionDTO> listadto = new ArrayList<>();
            inversion.stream().forEach(i -> {
                InversionDTO dto = new InversionDTO();
                dto.setId_inver(i.getId());
                dto.setMonto(i.getMonto());
                dto.setCuotas(i.getCuotas());
                dto.setRetorno(i.getRetorno());
                dto.setGanancias(i.getGanancias());
                dto.setMontomensual(i.getMonto_mensual());
                dto.setCuotasFaltantes(i.getCuotas_faltantes());
                dto.setFechaCreacion(i.getFecha_creacion());
                dto.setRiesgo(i.getRiesgo().getNombre_riesgo().toString());
                dto.setCosto(i.getRiesgo().getCosto());
                dto.setMicroEmprendimiento(i.getMicroEmprendimiento().getNombre());
                dto.setEstado(i.getEstado());
                listadto.add(dto);

            });
            return listadto;
        }




   /* public List<InversionDTO> obtenerMisInversiones(Long id) {
        try {
            Optional<Usuario> user = usuarioRepositorio.findById(id);
            if (user.isPresent()) {
                List<Object[]> resultados = inversionRepository.obtenerMisInversiones(id);
                List<InversionDTO> cantidad = new ArrayList<>();
                for (Object[] resultado : resultados) {
                    Long id_inver = (Long) resultado[0];
                    Long monto = (Long) resultado[1];
                    Long cuotas = (Long) resultado[2];
                    Long retorno = (Long) resultado[3];
                    Long ganancias = (Long) resultado[4];
                    Long montomensual = (Long) resultado[5];
                    Long cuotas_faltantes = (Long) resultado[6];
                    LocalDate fecha_creacion = (LocalDate) resultado[7];
                    Riesgo riesgo = (Riesgo) resultado[8];
                    MicroEmprendimiento microEmprendimiento = (MicroEmprendimiento) resultado[9];
                    Boolean estado = (Boolean) resultado[10];
                    InversionDTO cantdto = new InversionDTO(
                            id_inver,
                            monto,
                            cuotas,
                            retorno,
                            ganancias,
                            montomensual,
                            cuotas_faltantes,
                            fecha_creacion,
                            String.valueOf(riesgo.getNombre_riesgo()),
                            microEmprendimiento.getNombre(),
                            estado
                            );
                    cantidad.add(cantdto);
                }
                return cantidad;
            }else {
                return Collections.emptyList(); // Retorna una lista vacía si el usuario no existe
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener la cantidad por Rubro", e);
        }

    }
*/

}

