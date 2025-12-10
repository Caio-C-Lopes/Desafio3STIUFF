import { useRef, type FormEvent } from 'react'
import { api } from '../services/api'
import Navbar from '../components/NavBar'

export default function CadastrarHistoricoPage() {

  const matriculaRef = useRef<HTMLInputElement | null>(null)
  const codigoDiscRef = useRef<HTMLInputElement | null>(null)
  const codigoCursoRef = useRef<HTMLInputElement | null>(null)
  const notaRef = useRef<HTMLInputElement | null>(null)
  const anoSemestreRef = useRef<HTMLInputElement | null>(null)

  async function handleSubmit(event: FormEvent) {
    event.preventDefault()

    if (
      !matriculaRef.current?.value ||
      !codigoDiscRef.current?.value ||
      !codigoCursoRef.current?.value ||
      !notaRef.current?.value ||
      !anoSemestreRef.current?.value
    ) {
      alert("Preencha todos os campos.")
      return
    }

    try {
      const response = await api.post("/historicos", {
        matriculaAluno: matriculaRef.current.value,
        codigoDisciplina: codigoDiscRef.current.value,
        codigoCurso: codigoCursoRef.current.value,
        nota: Number(notaRef.current.value),
        anoSemestre: anoSemestreRef.current.value
      })

      console.log("Histórico cadastrado:", response.data)
      alert("Histórico cadastrado com sucesso")

      matriculaRef.current.value = ""
      codigoDiscRef.current.value = ""
      codigoCursoRef.current.value = ""
      notaRef.current.value = ""
      anoSemestreRef.current.value = ""

    } catch (error: any) {
      console.error("Erro ao cadastrar histórico:", error)
      if (error.response) {
        alert(error.response.data)
      }
    }
  }

  return (
    <div className="w-full min-h-screen bg-gray-900 flex flex-col">
      <Navbar />

      <main className="mt-24 my-10 w-full md:max-w-2xl mx-auto px-4">
        <h1 className="text-4xl font-medium text-white">Cadastrar Histórico</h1>

        <form className="flex flex-col my-6" onSubmit={handleSubmit}>

          <label className="font-medium text-white">Matrícula do Aluno:</label>
          <input
            type="text"
            className="w-full mb-5 p-2 rounded bg-gray-800 text-white placeholder-gray-400"
            placeholder="Digite a matrícula"
            ref={matriculaRef}
          />

          <label className="font-medium text-white">Código da Disciplina:</label>
          <input
            type="text"
            className="w-full mb-5 p-2 rounded bg-gray-800 text-white placeholder-gray-400"
            placeholder="Digite o código"
            ref={codigoDiscRef}
          />

          <label className="font-medium text-white">Código do Curso:</label>
          <input
            type="text"
            className="w-full mb-5 p-2 rounded bg-gray-800 text-white placeholder-gray-400"
            placeholder="Digite o código do curso"
            ref={codigoCursoRef}
          />

          <label className="font-medium text-white">Nota:</label>
          <input
            type="text"
            className="w-full mb-5 p-2 rounded bg-gray-800 text-white placeholder-gray-400 appearance-none"
            placeholder="Digite a nota"
            ref={notaRef}
          />

          <label className="font-medium text-white">Ano/Semestre:</label>
          <input
            type="text"
            className="w-full mb-5 p-2 rounded bg-gray-800 text-white placeholder-gray-400"
            placeholder="Ex: 20231"
            ref={anoSemestreRef}
          />

          <input
            type="submit"
            value="Cadastrar"
            className="cursor-pointer w-full p-2 mt-2 bg-green-500 rounded font-medium hover:bg-green-600 duration-200"
          />
        </form>

      </main>
    </div>
  )
}
