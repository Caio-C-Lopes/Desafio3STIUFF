import { useRef, useState } from 'react'
import { api } from '../services/api'
import Navbar from '../components/NavBar'
import type { FormEvent } from 'react'

interface HistoricoProps {
  matricula: string
  codDisciplina: string
  codCurso: string
  nota: string
  cargaHoraria: string
  anoSemestre: string
}

export default function BuscarHistoricosDeUmAlunoPage() {
  const [historicos, setHistoricos] = useState<HistoricoProps[]>([])
  const matriculaRef = useRef<HTMLInputElement | null>(null)

  async function handleSearch(event: FormEvent) {
    event.preventDefault()
    if (!matriculaRef.current?.value) return

    const matricula = matriculaRef.current.value

    try {
      const response = await api.get(`/historicos/aluno/${matricula}`)
      setHistoricos(response.data)
    } catch (error: any) {
      console.error("Erro ao buscar históricos:", error)
      alert("Nenhum histórico encontrado para essa matrícula.")
    }
  }

  return (
    <div className="w-full min-h-screen bg-gray-900 flex flex-col">
      <Navbar />
      <main className="mt-24 my-10 w-full md:max-w-2xl mx-auto px-4">
        <h1 className="text-4xl font-medium text-white">Buscar Histórico do Aluno</h1>

        <form className="flex flex-col my-6" onSubmit={handleSearch}>
          <label className="font-medium text-white">Matrícula:</label>

          <input
            type="text"
            placeholder="Digite a matrícula:"
            className="w-full mb-5 p-2 rounded text-white bg-gray-800 placeholder-gray-400"
            ref={matriculaRef}
          />

          <input
            type="submit"
            value="Buscar"
            className="cursor-pointer w-full p-2 bg-green-500 rounded font-medium hover:bg-green-600 duration-200"
          />
          
        </form>

        <section className="flex flex-col gap-4">
          {historicos.map((h, index) => (
            <div
              key={index}
              className="w-full bg-white rounded p-3 shadow-lg hover:scale-105 duration-200"
            >
              <p><span className="font-medium">Matrícula:</span> {h.matricula}</p>
              <p><span className="font-medium">Disciplina:</span> {h.codDisciplina}</p>
              <p><span className="font-medium">Curso:</span> {h.codCurso}</p>
              <p><span className="font-medium">Nota:</span> {h.nota}</p>
              <p><span className="font-medium">Carga Horária:</span> {h.cargaHoraria}</p>
              <p><span className="font-medium">Ano/Semestre:</span> {h.anoSemestre}</p>
            </div>
          ))}
        </section>
      </main>
    </div>
  )
}
