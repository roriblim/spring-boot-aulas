package br.com.rosana.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rosana.converter.DozerConverter;
import br.com.rosana.data.model.Book;
import br.com.rosana.data.model.Person;
import br.com.rosana.data.vo.BookVO;
import br.com.rosana.data.vo.PersonVO;
import br.com.rosana.exception.ResourceNotFoundException;
import br.com.rosana.repository.BookRepository;
import br.com.rosana.repository.PersonRepository;

    //@service é usado para que o spring cuide da injeção de dependência dessa classe.
	//quando for usar essa classe em outra classe, nao precisa dar um new, nem deixar isso static
	@Service
	public class BookServices{
		
		@Autowired
		BookRepository bookrepository;
		
		public BookVO create(BookVO bookVO) {
		
			var entity = DozerConverter.parseObject(bookVO, Book.class); 
			var vo = DozerConverter.parseObject(bookrepository.save(entity), BookVO.class); //salvo no repositório e converto novamente para retornar ao usuário como VO
			return vo;
			}
		
		public BookVO update(BookVO bookVO) {
			
			var entity = bookrepository.findById(bookVO.getKey())
			         .orElseThrow(()-> new ResourceNotFoundException("No records found for this ID"));	
			entity.setAuthor(bookVO.getAuthor());
			entity.setLaunchDate(bookVO.getLaunchDate());
			entity.setPrice(bookVO.getPrice());
			entity.setTitle(bookVO.getTitle());			
			var vo = DozerConverter.parseObject(bookrepository.save(entity), BookVO.class);
			return vo;
		}
		
		public void delete (Long id){
			Book entity = bookrepository.findById(id)
			         .orElseThrow(()-> new ResourceNotFoundException("No records found for this ID"));
			bookrepository.delete(entity);
			//recbe o id, vai até a base de dados, se existir o id, deleta, se não, retorna uma exceção
		}
		
		public BookVO findById(Long id) {
		var entity = bookrepository.findById(id)
				         .orElseThrow(()-> new ResourceNotFoundException("No records found for this ID"));
		return DozerConverter.parseObject(entity, BookVO.class);
			
		}
		
		public List<BookVO> findAll() {
			return DozerConverter.parseListObjects(bookrepository.findAll(), BookVO.class);
		}
		

	}

