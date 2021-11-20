package br.com.rosana;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rosana.data.vo.BookVO;
import br.com.rosana.services.BookServices;

@RestController
@RequestMapping("/api/book/v1")
public class BookController {

		@Autowired
		private BookServices services;
		

		@GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
		public List<BookVO> mostraTodos() {
			List<BookVO> books = services.findAll();
			books.stream().forEach(b->b.add(linkTo(methodOn(BookController.class).mostraUm(b.getKey())).withSelfRel()));
			return books;
		}
		
		@GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
		public BookVO mostraUm(@PathVariable("id")Long id) {
			BookVO bookVO = services.findById(id);
			bookVO.add(linkTo(methodOn(BookController.class).mostraUm(id)).withSelfRel());
			return bookVO;
		}
		
		@PostMapping(produces = {"application/json", "application/xml", "application/x-yaml"}, 
				consumes = {"application/json", "application/xml", "application/x-yaml"})
		public BookVO poeNoBanco(@RequestBody BookVO bookVO) {
			BookVO bookVOcriado = services.create(bookVO);
			bookVOcriado.add(linkTo(methodOn(BookController.class).mostraUm(bookVOcriado.getKey())).withSelfRel());
			return bookVOcriado;
		}
		
		@PutMapping(produces = {"application/json", "application/xml", "application/x-yaml"}, 
				consumes = {"application/json", "application/xml", "application/x-yaml"})	
		public BookVO atualizaNoBanco(@RequestBody BookVO bookVO) {
			BookVO bookVOatualizado = services.update(bookVO);
			bookVOatualizado.add(linkTo(methodOn(BookController.class).mostraUm(bookVO.getKey())).withSelfRel());
			//obs. aqui em cima, poderia colocar tb bookVOatualizado.getkey() no findbyid, pois o parametro de entrada já tem a key nesse caso.
			return bookVOatualizado;
		}
		
		@DeleteMapping("/{id}")
		public ResponseEntity<?> deletaUm(@PathVariable("id") Long id) {
			services.delete(id);
			return ResponseEntity.ok().build();
}
		
}