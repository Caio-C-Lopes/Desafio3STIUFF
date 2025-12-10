import { useEffect, useState } from 'react'
import { api } from '../services/api'
import Navbar from '../components/NavBar'

interface HistoricoProps{
  id: string
  matricula: string 
  codDisciplina: string 
  codCurso: string 
  nota: string
  cargaHoraria: string
  anoSemestre: string
}

export default function HistoricosPage() {
  const [historicos, setHistoricos] = useState<HistoricoProps[]>([])

  useEffect(() => {
    loadHistoricos()
  }, [])

  async function loadHistoricos() {
    try {
      const response = await api.get("/historicos")
      setHistoricos(response.data)
    } catch (error) {
      console.error("Erro ao carregar históricos:", error)
    }
  }

  return (
    <div className="w-full min-h-screen bg-gray-900 flex flex-col">
      <Navbar />

      <main className="mt-24 my-10 w-full md:max-w-3xl mx-auto px-4">
        <h1 className="text-4xl font-medium text-white mb-6">Históricos</h1>

        <section className="flex flex-col gap-3">
          {historicos.map(historico => (
            <div
              key={historico.id}
              className="w-full bg-white rounded p-2 relative hover:scale-105 duration-200"
            >
              <div>
                <p><span className="font-medium">Aluno:</span> {historico.matricula}</p>
                <p><span className="font-medium">Disciplina:</span> {historico.codDisciplina}</p>
                <p><span className="font-medium">Curso:</span> {historico.codCurso}</p>
                <p><span className="font-medium">Nota:</span> {historico.nota}</p>
                <p><span className="font-medium">Carga Horária:</span> {historico.cargaHoraria}</p>
                <p><span className="font-medium">Ano/Semestre:</span> {historico.anoSemestre}</p>
              </div>
            </div>
          ))}
        </section>
      </main>
    </div>
  )
}
