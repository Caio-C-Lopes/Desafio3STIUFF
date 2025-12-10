import { createBrowserRouter } from 'react-router-dom'

import HomePage from '../pages/HomePage'
import AlunosPage from '../pages/AlunosPage'
import DisciplinasPage from '../pages/DisciplinasPage'
import CursosPage from '../pages/CursosPage'
import HistoricosPage from '../pages/HistoricosPage'
import CRPage from '../pages/CRPage'
import CRPorCursoPage from '../pages/CRPorCursoPage'
import CadastrarHistoricoPage from '../pages/CadastrarHistoricoPage'
import BuscarHistoricosDeUmAlunoPage from '../pages/BuscarHistoricosDeUmAlunoPage'

export const router = createBrowserRouter([
  { path: '/', element: <HomePage /> },
  { path: '/alunos', element: <AlunosPage /> },
  { path: '/cursos', element: <CursosPage /> },
  { path: '/disciplinas', element: <DisciplinasPage /> },
  { path: '/historicos', element: <HistoricosPage /> },
  { path: '/historicos/aluno', element: <BuscarHistoricosDeUmAlunoPage /> },
  { path: '/cr', element: <CRPage /> },
  { path: '/crporcurso', element: <CRPorCursoPage /> },
  { path: '/historicos/cadastrar', element: <CadastrarHistoricoPage /> }
])
