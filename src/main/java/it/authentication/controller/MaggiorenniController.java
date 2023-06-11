package it.authentication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.authentication.model.dto.MaggiorenniDTO;
import it.authentication.model.dto.NewPlayerDTO;
import it.authentication.model.dto.ResponseDTO;
import it.authentication.model.entity.Maggiorenni;
import it.authentication.model.entity.Minorenni;
import it.authentication.model.entity.VerificationToken;
import it.authentication.repository.MaggiorenniRepository;
import it.authentication.repository.MinorenniRepository;
import it.authentication.repository.VerificationTokenRepository;
import it.authentication.service.BaseService;
import it.authentication.service.MaggiorenniService;
import it.authentication.service.NewMaggiorenneService;

@CrossOrigin
@RestController
@RequestMapping("/api/maggiorenne")
public class MaggiorenniController extends BaseService {
	@Autowired
	private NewMaggiorenneService nms;
	@Autowired
	private MaggiorenniRepository mr;
	@Autowired
	private MinorenniRepository minr;
	@Autowired
	private VerificationTokenRepository vtr;
	@Autowired
	MaggiorenniService ms;

	@PostMapping("/add")
	public ResponseDTO add(@RequestBody NewPlayerDTO newPlayerDTO) {
		responseReset();
		try {
			response = nms.newMagg(newPlayerDTO);
		} catch (Exception e) {
			response.setStatus(false);
			response.setError(e.getMessage());
		}
		response.setMessage(response.getStatus() == true ? "Controlla la casella di posta clicca il link per Confermare il tuo indirizzo email" : "ops - registrazione fallita -");
		return response;
	}

	@GetMapping("/registrationConfirm")
	public ResponseDTO registrationConfirm(@RequestParam("token") String token) {
		responseReset();
		response = nms.verification(token);
		return response;

	}

	@GetMapping("/getAll")
	public ResponseDTO all() {
		List<Maggiorenni> ma = mr.findAll();
		response.getContent().put("maggiorenni", ma);
		return response;
	}

	@PostMapping("/uuid")
	public ResponseDTO elimina(@RequestParam("UUID") java.util.UUID UUID) {
		responseReset();
		// java.util.UUID u =
		// java.util.UUID.fromString("9aca4cfc-45ed-4e24-aec2-1340ca2c54e6");

		Maggiorenni ma = mr.findByMaggiorenniUUID(UUID);
		for (Minorenni min : ma.getMinorennis()) {
			minr.delete(min);
		}
		VerificationToken vt = vtr.findByMaggiorenneId(ma.getIdMaggiorenne());
		if (vt != null) {
			vtr.delete(vt);
		}
		mr.deleteById(ma.getIdMaggiorenne());
		response.getContent().put("maggiorenne", ma);
		return response;

	}

	@PutMapping("/upDate")
	public ResponseDTO upLoad(@RequestBody MaggiorenniDTO maggiorenneDTO) {
		return ms.upLoad(maggiorenneDTO);
	}

}
