package howl.in.disguise;

import java.net.URI;
import java.util.*;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class TransformerResource {

	@Autowired
	private TransformerRepository transformerRepository;

	@GetMapping("/transformers")
	public List<Transformer> retrieveAllTransformers() {
		return transformerRepository.findAll();
	}

	@GetMapping("/transformers/{id}")
	public Transformer retrieveTransformer(@PathVariable long id) {
		Optional<Transformer> transformer = transformerRepository.findById(id);

		if (!transformer.isPresent())
			throw new TransformerNotFoundException("id-" + id);

		return transformer.get();
	}


	@GetMapping("/transformers/sort")
    public ArrayList<ArrayList<Transformer>> sortAllTransformers(){


        List<Transformer> autoBotsSorted = transformerRepository.findAll(Sort.by(Sort.Direction.DESC,"rank"));
        Predicate<Transformer> isAutoBot = transformer -> transformer.getAllegiance().equals("Autobot");

        List<Transformer> decepticonsSorted = transformerRepository.findAll(Sort.by(Sort.Direction.DESC,"rank"));
        Predicate<Transformer> isDecepticon = transformer -> transformer.getAllegiance().equals("Decepticon");

        autoBotsSorted.removeIf(isAutoBot);
        decepticonsSorted.removeIf(isDecepticon);

        ArrayList<ArrayList<Transformer>> allBotsSorted = new ArrayList<ArrayList<Transformer>>();
        allBotsSorted.add((ArrayList<Transformer>) autoBotsSorted);
        allBotsSorted.add((ArrayList<Transformer>) decepticonsSorted);

        return allBotsSorted;
    }

    @GetMapping("/battle/{transformerIds}")
    public String battle(@PathVariable String transformerIds) {
        String[] contenderIds = transformerIds.split(",");

        List<Transformer> contenders = new ArrayList<Transformer>();
        for (String contender : contenderIds) {
            Optional<Transformer> transformer = transformerRepository.findById(Long.valueOf(contender));

            if (!transformer.isPresent()) {
                throw new TransformerNotFoundException("id-" + contender);
            }
            contenders.add(transformer.get());
        }
        //currently have all transformers in "contenders"
        //Send contenders into battle!
        BattleSim battleSim = new BattleSim();

        return battleSim.fight(contenders);

    }

	@DeleteMapping("/transformers/{id}")
	public void deleteTransformer(@PathVariable long id) {
		transformerRepository.deleteById(id);
	}

	@PostMapping("/transformers")
	public ResponseEntity<Object> createTransformer(@RequestBody Transformer transformer) {
		Transformer savedTransformer = transformerRepository.save(transformer);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedTransformer.getId()).toUri();

		return ResponseEntity.created(location).build();

	}
	
	@PutMapping("/transformers/{id}")
	public ResponseEntity<Object> updateTransformer(@RequestBody Transformer transformer, @PathVariable long id) {

		Optional<Transformer> transformerOptional = transformerRepository.findById(id);

		if (!transformerOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

		transformer.setId(id);
		transformerRepository.save(transformer);

		return ResponseEntity.noContent().build();
	}

}
